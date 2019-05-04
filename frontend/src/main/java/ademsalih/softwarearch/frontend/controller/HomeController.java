package ademsalih.softwarearch.frontend.controller;

import ademsalih.softwarearch.frontend.model.*;
import ademsalih.softwarearch.frontend.service.*;
import ademsalih.softwarearch.frontend.tools.TimeFormatter;
import ademsalih.softwarearch.frontend.tools.URLShortener;
import ademsalih.softwarearch.frontend.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    TweetService tweetService;

    @Autowired
    UserService userService;

    @Autowired
    FollowService followService;

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();

        User serverProfile = userService.getUserById(user_id);
        model.addAttribute("profile", serverProfile);

        model.addAttribute("retweet", new Retweet());
        model.addAttribute("query", query);

        List<Tweet> searchResultTweets = tweetService.getSearchResultTweets(query);

        Collections.sort(searchResultTweets, Comparator.comparing(Tweet::getDateTime));
        Collections.reverse(searchResultTweets);


        List<UserTweet> feedTweets = new ArrayList<>();

        for (Tweet tweet : searchResultTweets) {

            User user = userService.getUserById(tweet.getUser());

            UserTweet userTweet = new UserTweet(
                    user.getUser_id(),
                    tweet.getId(),
                    user.getName(),
                    user.getUserName(),
                    user.getProfileImageName(),
                    new TimeFormatter().formatTimeAgo(tweet.getDateTime()),
                    tweet.getImageName(),
                    tweet.getMessage()
            );

            if (tweet.getNewTweet() != null) {

                User retweeter = userService.getUserById(tweet.getNewTweet().getUser());

                UserTweet retweet = new UserTweet(
                        retweeter.getUser_id(),
                        tweet.getNewTweet().getId(),
                        retweeter.getName(),
                        retweeter.getUserName(),
                        retweeter.getProfileImageName(),
                        new TimeFormatter().formatTimeAgo(tweet.getNewTweet().getDateTime()),
                        tweet.getNewTweet().getImageName(),
                        tweet.getNewTweet().getMessage()
                );

                userTweet.setNewTweet(retweet);
            }

            feedTweets.add(userTweet);
        }

        model.addAttribute("feedTweets", feedTweets);

        return "search-results";
    }

    @GetMapping("/signup")
    public String signup(SignUpUser signUpUser, Model model) {
        return "signup";
    }

    @PostMapping("/register")
    public String register(@Valid SignUpUser signUpUser, BindingResult bindingResult) {

        User userNameUser = !signUpUser.getUserName().isEmpty() ? userService.getUserByUsername(signUpUser.getUserName()) : null;
        if (userNameUser != null) bindingResult.rejectValue("userName", "error.user", "An account already exists with this username");

        User emailUser = !signUpUser.getEmail().isEmpty() ? userService.getUserByEmail(signUpUser.getEmail()) : null;
        if (emailUser != null) bindingResult.rejectValue("email", "error.user", "An account already exists with this email");


        if (bindingResult.hasErrors()) {
            return "signup";
        }

        User user = new User();

        user.setName(signUpUser.getName());
        user.setEmail(signUpUser.getEmail());
        user.setUserName(signUpUser.getUserName());
        user.setPassword(signUpUser.getPassword());

        user.setUserRole("USER");
        user.setBannerImageName("default-banner-image.jpg");
        user.setProfileImageName("default-profile-image.jpg");
        user.setLocation("");
        user.setLink("");
        user.setAccountCreated(Calendar.getInstance().getTime().toInstant().toString());

        userService.registerUser(user);

        return "redirect:/home";
    }

    @GetMapping("/profileaccount")
    public String profileAccount(AccountEditUser accountEditUser, Model model) {
        model.addAttribute("settingNumber", 0);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();

        User serverProfile = userService.getUserById(user_id);
        model.addAttribute("profile", serverProfile);


        accountEditUser.setName(serverProfile.getName());
        accountEditUser.setUserName(serverProfile.getUserName());
        accountEditUser.setEmail(serverProfile.getEmail());

        return "profile-account";
    }

    @PostMapping("/profileaccountsave")
    public String profileAccountSave(
            @Valid AccountEditUser accountEditUser,
            BindingResult bindingResult,
            HttpServletRequest httpServletRequest,
            Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();


        User userNameUser = userService.getUserByUsername(accountEditUser.getUserName());

        if (userNameUser != null && userNameUser.getUser_id() != user_id) {
            bindingResult.rejectValue("userName", "error.user", "An account already exists with this username");
        }

        User emailUser = userService.getUserByEmail(accountEditUser.getEmail());

        if (emailUser != null && emailUser.getUser_id() != user_id) {
            if (emailUser != null) bindingResult.rejectValue("email", "error.user", "An account already exists with this email");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("settingNumber", 0);

            User serverProfile = userService.getUserById(user_id);
            model.addAttribute("profile", serverProfile);

            return "profile-account";
        }

        User serverUser = userService.getUserById(user_id);


        Collection<SimpleGrantedAuthority> nowAuthorities =(Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(accountEditUser.getUserName(), serverUser.getPassword(), nowAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);



        serverUser.setName(accountEditUser.getName());
        serverUser.setEmail(accountEditUser.getEmail());
        serverUser.setUserName(accountEditUser.getUserName());
        userService.updateUser(serverUser);
        return "redirect:/profileaccount";
    }

    @GetMapping("/profilepassword")
    public String profilePassword(PasswordEditUser passwordEditUser, Model model) {
        model.addAttribute("settingNumber", 1);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();

        User serverProfile = userService.getUserById(user_id);
        model.addAttribute("profile", serverProfile);

        return "profile-password";
    }

    @PostMapping("/profilepasswordsave")
    public String profilePasswordSave(
            @Valid PasswordEditUser user,
            BindingResult bindingResult,
            HttpServletRequest httpServletRequest,
            Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();

        if (bindingResult.hasErrors()) {
            model.addAttribute("settingNumber", 1);

            User serverProfile = userService.getUserById(user_id);
            model.addAttribute("profile", serverProfile);
            return "profile-password";
        }

        User serverUser = userService.getUserById(user_id);
        serverUser.setPassword(user.getPassword());

        userService.updateUser(serverUser);
        return "redirect:/profilepassword";
    }

    @GetMapping("/profilepicture")
    public String profilePicture(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();

        User profile = userService.getUserById(user_id);
        model.addAttribute("profile", profile);

        model.addAttribute("picuser", new PictureEditUser());


        model.addAttribute("settingNumber", 2);
        return "profile-pictures";
    }

    @PostMapping("/profilepicturesave")
    public String profilePictureSave(@ModelAttribute("picuser") PictureEditUser user,
                                     HttpServletRequest httpServletRequest,
                                     @RequestParam(value = "profileImageName", required=false) MultipartFile profileImage,
                                     @RequestParam(value = "bannerImageName", required=false) MultipartFile bannerImage) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();

        if (profileImage.isEmpty() && bannerImage.isEmpty()) {
            String referer = httpServletRequest.getHeader("Referer");
            return "redirect:" + referer;
        }

        User serverUser = userService.getUserById(user_id);

        if (!profileImage.isEmpty()) {
            try {
                String IMAGE_LOCATION = "/src/main/upload/static/images/profile/";

                String projectDir = System.getProperty("user.dir");
                String absolutePath = projectDir + IMAGE_LOCATION;

                Path path = Paths.get(absolutePath + profileImage.getOriginalFilename());

                Files.write(path,profileImage.getBytes());

                serverUser.setProfileImageName(user.getProfileImageName().getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!bannerImage.isEmpty()) {
            try {
                String IMAGE_LOCATION = "/src/main/upload/static/images/banner/";

                String projectDir = System.getProperty("user.dir");
                String absolutePath = projectDir + IMAGE_LOCATION;

                Path path = Paths.get(absolutePath + bannerImage.getOriginalFilename());

                Files.write(path,bannerImage.getBytes());

                serverUser.setBannerImageName(user.getBannerImageName().getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        userService.updateUser(serverUser);
        return "redirect:/profilepicture";
    }

    @GetMapping("/profileother")
    public String profileOther(OtherEditUser otherEditUser, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();

        model.addAttribute("settingNumber", 3);

        User serverProfile = userService.getUserById(user_id);
        model.addAttribute("profile", serverProfile);

        otherEditUser.setPhone(serverProfile.getPhone());
        otherEditUser.setBio(serverProfile.getBio());
        otherEditUser.setLink(serverProfile.getLink());
        otherEditUser.setLocation(serverProfile.getLocation());


        List<String> locations = new ArrayList<>();
        locations.add("Oslo, Norway");
        locations.add("Istanbul, Turkey");
        locations.add("London, UK");
        locations.add("Berlin, Germany");
        locations.add("Dublin, Ireland");
        locations.add("Ankara, Turkey");
        locations.add("Bergen, Norway");

        model.addAttribute("locations", locations);
        return "profile-other";
    }

    @PostMapping("/profileothersave")
    public String profileOtherSave(
            @Valid OtherEditUser otherEditUser,
            BindingResult bindingResult,
            HttpServletRequest httpServletRequest,
            Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();

        if (bindingResult.hasErrors()) {
            model.addAttribute("settingNumber", 3);

            User serverProfile = userService.getUserById(user_id);
            model.addAttribute("profile", serverProfile);

            List<String> locations = new ArrayList<>();
            locations.add("Oslo, Norway");
            locations.add("Istanbul, Turkey");
            locations.add("London, UK");
            locations.add("Berlin, Germany");
            locations.add("Dublin, Ireland");
            locations.add("Ankara, Turkey");
            locations.add("Bergen, Norway");

            model.addAttribute("locations", locations);
            return "profile-other";
        }

        User serverUser = userService.getUserById(user_id);

        serverUser.setPhone(otherEditUser.getPhone());
        serverUser.setBio(otherEditUser.getBio());
        serverUser.setLink(otherEditUser.getLink());
        serverUser.setLocation(otherEditUser.getLocation());

        userService.updateUser(serverUser);
        return "redirect:/profileother";
    }

    @GetMapping("/deleteaccount")
    public String deleteAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        tweetService.deleteTweetsByUser(authUser.getUser_id());
        userService.deleteUser(authUser.getUser_id());
        return "redirect:/logout";
    }

    @PostMapping("/follow/{id}")
    public String follow(@PathVariable long id, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();

        Follow follow = new Follow();

        follow.setUser(userService.getUserById(user_id));
        follow.setFollowing_user(userService.getUserById(id));

        followService.registerFollow(follow);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/unfollow/{id}")
    public String unfollow(@PathVariable long id, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();

        followService.deleteFollow(user_id, id);

        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/following/{user_id}/{following_id}")
    public String following(@PathVariable("user_id") long user_id, @PathVariable("following_id") long following_id) {

        FollowStatus f = followService.getFollowingStatus(user_id, following_id);

        String s = String.valueOf(f.isFollow());

        return s;
    }

    @GetMapping("/{username}/following")
    public String followings(@PathVariable String username, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();
        long id = userService.getUserByUsername(username).getUser_id();

        model.addAttribute("retweet", new Retweet());
        model.addAttribute("follow", new Follow());

        model.addAttribute("group", 2);
        model.addAttribute("shortUrl", new URLShortener().shorten(authUser.getLink()));
        model.addAttribute("title", "People followed by " + authUser.getName() + " (@" + authUser.getUserName() + ") | Twitter");

        model.addAttribute("currentUser", user_id);

        // get user information for that user
        User profileUser = userService.getUserById(id);
        profileUser.setAccountCreated(new TimeFormatter().formatJoinDate(profileUser.getAccountCreated()));

        model.addAttribute("profile", profileUser);

        FollowStatus f = followService.getFollowingStatus(user_id, id);

        model.addAttribute("isFollowing", f.isFollow());


        // Get follower count the logged in user
        List<Follow> followers = followService.getFollowersForUserById(id);
        int followerCount = followers.size();
        model.addAttribute("followers", followerCount);

        // Get following count the logged in user
        List<Follow> following = followService.getFollowingsForUserById(id);
        int followingCount = following.size();
        model.addAttribute("followings", followingCount);

        // Get total tweet count the logged in user
        List<Tweet> userTweets = tweetService.getTweetsForUserById(id);
        List<Tweet> userRetweets = tweetService.getRetweetsForUserById(id);
        int totalTweets = userTweets.size() + userRetweets.size();
        model.addAttribute("tweetCount", totalTweets);



        List<Follow> follows = followService.getFollowingsForUserById(id);

        List<FollowPageUser> followingUsers = new ArrayList<>();

        for (Follow follow : follows) {
            long followingId = follow.getFollowing_user().getUser_id();

            User user = userService.getUserById(followingId);

            FollowStatus fs = followService.getFollowingStatus(user_id, user.getUser_id());

            FollowPageUser fpuser = new FollowPageUser(
                    user.getUser_id(),
                    user.getName(),
                    user.getUserName(),
                    user.getProfileImageName(),
                    user.getBannerImageName(),
                    user.getBio(),
                    Boolean.valueOf(fs.isFollow())
            );

            followingUsers.add(fpuser);
        }

        model.addAttribute("followUsers", followingUsers);


        return "profilefollow";
    }

    @GetMapping("/{username}/followers")
    public String followers(@PathVariable String username, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();
        long id = userService.getUserByUsername(username).getUser_id();

        model.addAttribute("group", 3);
        model.addAttribute("shortUrl", new URLShortener().shorten(authUser.getLink()));
        model.addAttribute("title", "People following " + authUser.getName() + " (@" + authUser.getUserName() + ") | Twitter");


        model.addAttribute("retweet", new Retweet());
        model.addAttribute("follow", new Follow());

        model.addAttribute("currentUser", user_id);

        // get user information for that user
        User profileUser = userService.getUserById(id);
        profileUser.setAccountCreated(new TimeFormatter().formatJoinDate(profileUser.getAccountCreated()));
        model.addAttribute("profile", profileUser);

        FollowStatus f = followService.getFollowingStatus(user_id, id);

        model.addAttribute("isFollowing", f.isFollow());


        // Get follower count the logged in user
        List<Follow> followers = followService.getFollowersForUserById(id);
        int followerCount = followers.size();
        model.addAttribute("followers", followerCount);

        // Get following count the logged in user
        List<Follow> following = followService.getFollowingsForUserById(id);
        int followingCount = following.size();
        model.addAttribute("followings", followingCount);

        // Get total tweet count the logged in user
        List<Tweet> userTweets = tweetService.getTweetsForUserById(id);
        List<Tweet> userRetweets = tweetService.getRetweetsForUserById(id);
        int totalTweets = userTweets.size() + userRetweets.size();
        model.addAttribute("tweetCount", totalTweets);



        List<FollowPageUser> followingUsers = new ArrayList<>();

        for (Follow follow : followers) {
            long followingId = follow.getUser().getUser_id();

            User user = userService.getUserById(followingId);

            FollowStatus fs = followService.getFollowingStatus(user_id, user.getUser_id());

            FollowPageUser fpuser = new FollowPageUser(
                    user.getUser_id(),
                    user.getName(),
                    user.getUserName(),
                    user.getProfileImageName(),
                    user.getBannerImageName(),
                    user.getBio(),
                    Boolean.valueOf(fs.isFollow())
            );

            followingUsers.add(fpuser);
        }

        model.addAttribute("followUsers", followingUsers);

        return "profilefollow";
    }

    @GetMapping("/{username}")
    public String profile(@PathVariable String username, Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();

        long id = userService.getUserByUsername(username).getUser_id();

        model.addAttribute("retweet", new Retweet());
        model.addAttribute("follow", new Follow());

        model.addAttribute("group", 1);
        model.addAttribute("shortUrl", new URLShortener().shorten(authUser.getLink()));


        // get user information for that user
        User profileUser = userService.getUserById(id);

        profileUser.setAccountCreated(new TimeFormatter().formatJoinDate(profileUser.getAccountCreated()));
        model.addAttribute("profile", profileUser);


        model.addAttribute("currentUser", user_id);

        FollowStatus f = followService.getFollowingStatus(user_id, id);

        model.addAttribute("isFollowing", f.isFollow());


        // Get follower count the logged in user
        List<Follow> followers = followService.getFollowersForUserById(id);
        int followerCount = followers.size();
        model.addAttribute("followers", followerCount);

        // Get following count the logged in user
        List<Follow> following = followService.getFollowingsForUserById(id);
        int followingCount = following.size();
        model.addAttribute("followings", followingCount);

        // Get total tweet count the logged in user
        List<Tweet> userTweets = tweetService.getTweetsForUserById(id);
        List<Tweet> userRetweets = tweetService.getRetweetsForUserById(id);
        int totalTweets = userTweets.size() + userRetweets.size();
        model.addAttribute("tweetCount", totalTweets);




        // get tweets for that user
        List<Tweet> newtweets = tweetService.getTweetsForUserById(id);
        List<Tweet> retweets = tweetService.getRetweetsForUserById(id);

        List<Tweet> tweets = new ArrayList<>();

        tweets.addAll(newtweets);
        tweets.addAll(retweets);

        Collections.sort(tweets, Comparator.comparing(Tweet::getDateTime));
        Collections.reverse(tweets);

        List<UserTweet> feedTweets = new ArrayList<>();

        for (Tweet tweet : tweets) {

            User user = userService.getUserById(tweet.getUser());

            UserTweet userTweet = new UserTweet(
                    user.getUser_id(),
                    tweet.getId(),
                    user.getName(),
                    user.getUserName(),
                    user.getProfileImageName(),
                    new TimeFormatter().formatTimeAgo(tweet.getDateTime()),
                    tweet.getImageName(),
                    tweet.getMessage()
            );

            if (tweet.getNewTweet() != null) {

                User retweeter = userService.getUserById(tweet.getNewTweet().getUser());

                UserTweet retweet = new UserTweet(
                        retweeter.getUser_id(),
                        tweet.getNewTweet().getId(),
                        retweeter.getName(),
                        retweeter.getUserName(),
                        retweeter.getProfileImageName(),
                        new TimeFormatter().formatTimeAgo(tweet.getNewTweet().getDateTime()),
                        tweet.getNewTweet().getImageName(),
                        tweet.getNewTweet().getMessage()
                );

                userTweet.setNewTweet(retweet);
            }

            feedTweets.add(userTweet);
        }


        model.addAttribute("feedTweets", feedTweets);
        List<User> suggestions = userService.getFollowSuggestions(user_id);
        model.addAttribute("newUsers", suggestions);

        return "profiletweets";
    }

    // TODO: ADD PRINCIPAL FOR USER ID

    @PostMapping("/tweet")
    public String tweet(@ModelAttribute("tweet") Tweet tweet, @RequestParam(value = "file", required=false) MultipartFile file, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();

        tweet.setUser(user_id);
        tweet.setNewTweet(null);
        tweet.setDateTime(Calendar.getInstance());


        if (!file.isEmpty()) {
            try {
                String IMAGE_LOCATION = "/src/main/upload/static/images/tweet/";

                String projectDir = System.getProperty("user.dir");
                String absolutePath = projectDir + IMAGE_LOCATION;

                Path path = Paths.get(absolutePath + file.getOriginalFilename());

                Files.write(path,file.getBytes());

                tweet.setImageName(file.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        tweetService.postTweet(tweet);

        return "redirect:/home?tweets=friends";
    }

    @PostMapping("/retweet/{id}")
    public String retweet(@ModelAttribute("retweet") Retweet retweet, @PathVariable("id") long id, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        long user_id = authUser.getUser_id();

        retweet.setDateTime(Calendar.getInstance());
        retweet.setUser(user_id);

        Tweet tweet = new Tweet();
        tweet.setId(id);
        retweet.setNewTweet(tweet);

        tweetService.postRetweet(retweet);

        String referer = request.getHeader("Referer");

        return "redirect:" + referer;
    }

    @GetMapping("/deleteTweet/{id}")
    public String deleteTweet(@PathVariable long id, HttpServletRequest request) {
        tweetService.deleteTweet(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/deleteRetweet/{id}")
    public String deleteRetweet(@PathVariable long id, HttpServletRequest request) {
        tweetService.deleteRetweet(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest httpServletRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userPrincipal = userService.getUserByUsername(auth.getName());

        long user_id = userPrincipal.getUser_id();

        User loggedInUser = userService.getUserById(user_id);
        model.addAttribute("profile", loggedInUser);

        model.addAttribute("currentUser", user_id);


        boolean tweetBoxActive = httpServletRequest.getParameter("tweet") == null ? false : true;
        model.addAttribute("tweetBoxActive", tweetBoxActive);


        // Models for tweeting and retweeting
        model.addAttribute("tweet", new Tweet());
        model.addAttribute("retweet", new Retweet());

        // Get follower count the logged in user
        List<Follow> followers = followService.getFollowersForUserById(user_id);
        int followerCount = followers.size();
        model.addAttribute("followerCount", followerCount);

        // Get following count the logged in user
        List<Follow> followings = followService.getFollowingsForUserById(user_id);
        int followingCount = followings.size();
        model.addAttribute("followingCount", followingCount);

        // Get total tweet count the logged in user
        List<Tweet> userTweets = tweetService.getTweetsForUserById(user_id);
        List<Tweet> userRetweets = tweetService.getRetweetsForUserById(user_id);
        int totalTweets = userTweets.size() + userRetweets.size();
        model.addAttribute("totalTweets", totalTweets);


        List<Tweet> tweetsList = new ArrayList<>();

        if (httpServletRequest.getParameter("tweets") != null) {
            if (httpServletRequest.getParameter("tweets").equals("friends")) {

                for (Follow f : followings) {

                    List<Tweet> tweets = tweetService.getTweetsForUserById(f.getFollowing_user().getUser_id());
                    List<Tweet> retweets = tweetService.getRetweetsForUserById(f.getFollowing_user().getUser_id());

                    tweetsList.addAll(tweets);
                    tweetsList.addAll(retweets);
                }

                tweetsList.addAll(userTweets);
                tweetsList.addAll(userRetweets);

            } else {
                tweetsList.addAll(tweetService.getFeed());
            }
        } else {
            return "redirect:/home?tweets=friends";
        }




        Collections.sort(tweetsList, Comparator.comparing(Tweet::getDateTime));
        Collections.reverse(tweetsList);

        List<UserTweet> feedTweets = new ArrayList<>();

        for (Tweet tweet : tweetsList) {

            User user = userService.getUserById(tweet.getUser());

            UserTweet userTweet = new UserTweet(
                    user.getUser_id(),
                    tweet.getId(),
                    user.getName(),
                    user.getUserName(),
                    user.getProfileImageName(),
                    new TimeFormatter().formatTimeAgo(tweet.getDateTime()),
                    tweet.getImageName(),
                    tweet.getMessage()
            );

            if (tweet.getNewTweet() != null) {

                User retweeter = userService.getUserById(tweet.getNewTweet().getUser());

                UserTweet retweet = new UserTweet(
                        retweeter.getUser_id(),
                        tweet.getNewTweet().getId(),
                        retweeter.getName(),
                        retweeter.getUserName(),
                        retweeter.getProfileImageName(),
                        new TimeFormatter().formatTimeAgo(tweet.getNewTweet().getDateTime()),
                        tweet.getNewTweet().getImageName(),
                        tweet.getNewTweet().getMessage()
                );

                userTweet.setNewTweet(retweet);
            }

            feedTweets.add(userTweet);
        }

        model.addAttribute("feedTweets", feedTweets);


        List<User> suggestions = userService.getFollowSuggestions(user_id);

        model.addAttribute("newUsers", suggestions);

        return "home";
    }

    @GetMapping("/all")
    public String loggedInAllTweets(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userPrincipal = userService.getUserByUsername(auth.getName());

        long user_id = userPrincipal.getUser_id();

        User loggedInUser = userService.getUserById(user_id);
        model.addAttribute("profile", loggedInUser);

        model.addAttribute("currentUser", user_id);

        // Models for tweeting and retweeting
        model.addAttribute("tweet", new Tweet());
        model.addAttribute("retweet", new Retweet());

        // Get follower count the logged in user
        List<Follow> followers = followService.getFollowersForUserById(user_id);
        int followerCount = followers.size();
        model.addAttribute("followerCount", followerCount);

        // Get following count the logged in user
        List<Follow> followings = followService.getFollowingsForUserById(user_id);
        int followingCount = followings.size();
        model.addAttribute("followingCount", followingCount);

        // Get total tweet count the logged in user
        List<Tweet> userTweets = tweetService.getTweetsForUserById(user_id);
        List<Tweet> userRetweets = tweetService.getRetweetsForUserById(user_id);
        int totalTweets = userTweets.size() + userRetweets.size();
        model.addAttribute("totalTweets", totalTweets);



        List<Tweet> tweetsList = tweetService.getFeed();


        Collections.sort(tweetsList, Comparator.comparing(Tweet::getDateTime));
        Collections.reverse(tweetsList);

        List<UserTweet> feedTweets = new ArrayList<>();

        for (Tweet tweet : tweetsList) {

            User user = userService.getUserById(tweet.getUser());

            UserTweet userTweet = new UserTweet(
                    user.getUser_id(),
                    tweet.getId(),
                    user.getName(),
                    user.getUserName(),
                    user.getProfileImageName(),
                    new TimeFormatter().formatTimeAgo(tweet.getDateTime()),
                    tweet.getImageName(),
                    tweet.getMessage()
            );

            if (tweet.getNewTweet() != null) {

                User retweeter = userService.getUserById(tweet.getNewTweet().getUser());

                UserTweet retweet = new UserTweet(
                        retweeter.getUser_id(),
                        tweet.getNewTweet().getId(),
                        retweeter.getName(),
                        retweeter.getUserName(),
                        retweeter.getProfileImageName(),
                        new TimeFormatter().formatTimeAgo(tweet.getNewTweet().getDateTime()),
                        tweet.getNewTweet().getImageName(),
                        tweet.getNewTweet().getMessage()
                );

                userTweet.setNewTweet(retweet);
            }

            feedTweets.add(userTweet);
        }

        model.addAttribute("feedTweets", feedTweets);

        return "frontpage";
    }

    @GetMapping("/")
    public String allTweets(Model model) {

        List<Tweet> tweetsList = tweetService.getFeed();


        model.addAttribute("retweet", new Retweet());


        Collections.sort(tweetsList, Comparator.comparing(Tweet::getDateTime));
        Collections.reverse(tweetsList);

        List<UserTweet> feedTweets = new ArrayList<>();

        for (Tweet tweet : tweetsList) {

            User user = userService.getUserById(tweet.getUser());

            UserTweet userTweet = new UserTweet(
                    user.getUser_id(),
                    tweet.getId(),
                    user.getName(),
                    user.getUserName(),
                    user.getProfileImageName(),
                    new TimeFormatter().formatTimeAgo(tweet.getDateTime()),
                    tweet.getImageName(),
                    tweet.getMessage()
            );

            if (tweet.getNewTweet() != null) {

                User retweeter = userService.getUserById(tweet.getNewTweet().getUser());

                UserTweet retweet = new UserTweet(
                        retweeter.getUser_id(),
                        tweet.getNewTweet().getId(),
                        retweeter.getName(),
                        retweeter.getUserName(),
                        retweeter.getProfileImageName(),
                        new TimeFormatter().formatTimeAgo(tweet.getNewTweet().getDateTime()),
                        tweet.getNewTweet().getImageName(),
                        tweet.getNewTweet().getMessage()
                );

                userTweet.setNewTweet(retweet);
            }

            feedTweets.add(userTweet);
        }

        model.addAttribute("feedTweets", feedTweets);

        return "frontpage";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

}
