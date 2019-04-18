package ademsalih.softwarearch.frontend.controller;

import ademsalih.softwarearch.frontend.model.*;
import ademsalih.softwarearch.frontend.service.FollowService;
import ademsalih.softwarearch.frontend.service.TimeFormatService;
import ademsalih.softwarearch.frontend.service.TweetService;
import ademsalih.softwarearch.frontend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    long user_id = 1;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/follow/{id}")
    public String follow(@PathVariable long id, HttpServletRequest request) {

        Follow follow = new Follow();

        follow.setUser(userService.getUserById(user_id));
        follow.setFollowing_user(userService.getUserById(id));

        followService.registerFollow(follow);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("unfollow/{id}")
    public String unfollow(@PathVariable long id, HttpServletRequest request) {

        followService.deleteFollow(user_id, id);

        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("/following/{user_id}/{following_id}")
    public String following(@PathVariable("user_id") long user_id, @PathVariable("following_id") long following_id) {

        FollowStatus f = followService.getFollowingStatus(user_id, following_id);

        String s = String.valueOf(f.isFollow());

        return s;
    }

    @GetMapping("/{id}/following")
    public String followings(@PathVariable long id, Model model) {


        model.addAttribute("retweet", new Retweet());
        model.addAttribute("follow", new Follow());

        model.addAttribute("group", 2);

        model.addAttribute("currentUser", user_id);

        // get user information for that user
        User profileUser = userService.getUserById(id);
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
                    user.getFirstName(),
                    user.getLastName(),
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

    @GetMapping("/{id}/followers")
    public String followers(@PathVariable long id, Model model) {

        model.addAttribute("group", 3);

        model.addAttribute("retweet", new Retweet());
        model.addAttribute("follow", new Follow());

        model.addAttribute("currentUser", user_id);

        // get user information for that user
        User profileUser = userService.getUserById(id);
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
                    user.getFirstName(),
                    user.getLastName(),
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

    @GetMapping("/{id}")
    public String profile(@PathVariable long id, Model model) {

        model.addAttribute("retweet", new Retweet());
        model.addAttribute("follow", new Follow());

        model.addAttribute("group", 1);

        // get user information for that user
        User profileUser = userService.getUserById(id);
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

            User user = userService.getUserById(tweet.getUser_id());

            UserTweet userTweet = new UserTweet(
                    user.getUser_id(),
                    tweet.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUserName(),
                    user.getProfileImageName(),
                    new TimeFormatService().format(tweet.getDateTime()),
                    tweet.getImageName(),
                    tweet.getMessage()
            );

            if (tweet.getNewTweet() != null) {

                User retweeter = userService.getUserById(tweet.getNewTweet().getUser_id());

                UserTweet retweet = new UserTweet(
                        retweeter.getUser_id(),
                        tweet.getNewTweet().getId(),
                        retweeter.getFirstName(),
                        retweeter.getLastName(),
                        retweeter.getUserName(),
                        retweeter.getProfileImageName(),
                        new TimeFormatService().format(tweet.getNewTweet().getDateTime()),
                        tweet.getNewTweet().getImageName(),
                        tweet.getNewTweet().getMessage()
                );

                userTweet.setNewTweet(retweet);
            }

            feedTweets.add(userTweet);
        }

        model.addAttribute("feedTweets", feedTweets);


        return "profiletweets";
    }

    // TODO: ADD PRINCIPAL FOR USER ID

    @PostMapping("/tweet")
    public String tweet(@ModelAttribute("tweet") Tweet tweet, @RequestParam(value = "file", required=false) MultipartFile file, HttpServletRequest request) {

        tweet.setUser_id(user_id);
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

        return "redirect:/home";
    }

    @PostMapping("/retweet/{id}")
    public String retweet(@ModelAttribute("retweet") Retweet retweet, @PathVariable("id") long id) {

        retweet.setDateTime(Calendar.getInstance());
        retweet.setUser_id(user_id);

        Tweet tweet = new Tweet();
        tweet.setId(id);
        retweet.setNewTweet(tweet);

        tweetService.postRetweet(retweet);

        return "redirect:/home";
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

    @GetMapping("/")
    public String feed(Model model) {

        List<Tweet> tweetsList = tweetService.getFeed();

        Collections.sort(tweetsList, Comparator.comparing(Tweet::getDateTime));
        Collections.reverse(tweetsList);

        List<UserTweet> feedTweets = new ArrayList<>();

        for (Tweet tweet : tweetsList) {

            User user = userService.getUserById(tweet.getUser_id());

            UserTweet userTweet = new UserTweet(
                    user.getUser_id(),
                    tweet.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUserName(),
                    user.getProfileImageName(),
                    new TimeFormatService().format(tweet.getDateTime()),
                    tweet.getImageName(),
                    tweet.getMessage()
            );

            if (tweet.getNewTweet() != null) {

                User retweeter = userService.getUserById(tweet.getNewTweet().getUser_id());

                UserTweet retweet = new UserTweet(
                        retweeter.getUser_id(),
                        tweet.getNewTweet().getId(),
                        retweeter.getFirstName(),
                        retweeter.getLastName(),
                        retweeter.getUserName(),
                        retweeter.getProfileImageName(),
                        new TimeFormatService().format(tweet.getNewTweet().getDateTime()),
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

    @GetMapping("/home")
    public String home(Model model) {

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




        List<Tweet> tweetsList = new ArrayList<>();

        for (Follow f : followings) {

            List<Tweet> tweets = tweetService.getTweetsForUserById(f.getFollowing_user().getUser_id());
            List<Tweet> retweets = tweetService.getRetweetsForUserById(f.getFollowing_user().getUser_id());

            tweetsList.addAll(tweets);
            tweetsList.addAll(retweets);
        }

        tweetsList.addAll(userTweets);
        tweetsList.addAll(userRetweets);


        Collections.sort(tweetsList, Comparator.comparing(Tweet::getDateTime));
        Collections.reverse(tweetsList);

        List<UserTweet> feedTweets = new ArrayList<>();

        for (Tweet tweet : tweetsList) {

            User user = userService.getUserById(tweet.getUser_id());

            UserTweet userTweet = new UserTweet(
                    user.getUser_id(),
                    tweet.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUserName(),
                    user.getProfileImageName(),
                    new TimeFormatService().format(tweet.getDateTime()),
                    tweet.getImageName(),
                    tweet.getMessage()
            );

            if (tweet.getNewTweet() != null) {

                User retweeter = userService.getUserById(tweet.getNewTweet().getUser_id());

                UserTweet retweet = new UserTweet(
                        retweeter.getUser_id(),
                        tweet.getNewTweet().getId(),
                        retweeter.getFirstName(),
                        retweeter.getLastName(),
                        retweeter.getUserName(),
                        retweeter.getProfileImageName(),
                        new TimeFormatService().format(tweet.getNewTweet().getDateTime()),
                        tweet.getNewTweet().getImageName(),
                        tweet.getNewTweet().getMessage()
                );

                userTweet.setNewTweet(retweet);
            }

            feedTweets.add(userTweet);
        }

        model.addAttribute("feedTweets", feedTweets);
        return "home";
    }

}
