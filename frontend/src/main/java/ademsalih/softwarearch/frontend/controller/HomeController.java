package ademsalih.softwarearch.frontend.controller;

import ademsalih.softwarearch.frontend.model.*;
import ademsalih.softwarearch.frontend.service.FollowService;
import ademsalih.softwarearch.frontend.service.TweetService;
import ademsalih.softwarearch.frontend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    TweetService tweetService;

    @Autowired
    UserService userService;

    @Autowired
    FollowService followService;

    long user_id = 1;

    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }

    @PostMapping("/tweet")
    public String tweet(@ModelAttribute("tweet") Tweet tweet, @RequestParam(value = "file", required=false) MultipartFile file) {

        tweet.setUser_id(user_id);
        tweet.setNewTweet(null);
        tweet.setDateTime("now");


        if (!file.isEmpty()) {
            try {

                String IMAGE_LOCATION = "/src/main/resources/static/images/tweet/";

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
    public String retweet(@ModelAttribute("retweet") Retweet retweet, @PathVariable long id) {

        retweet.setDateTime("08.04.2019 21:33");
        retweet.setUser_id(user_id);

        Tweet tweet = new Tweet();
        tweet.setId(id);
        retweet.setNewTweet(tweet);

        tweetService.postRetweet(retweet);

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {

        // Models for tweeting and retweeting
        model.addAttribute("tweet", new Tweet());
        model.addAttribute("retweet", new Retweet());

        // Get follower count the logged in user
        List<Follow> followers = followService.getFollowersForUserById(user_id);
        int followerCount = followers.size();
        model.addAttribute("followerCount", followerCount);

        // Get following count the logged in user
        List<Follow> following = followService.getFollowingsForUserById(user_id);
        int followingCount = following.size();
        model.addAttribute("followingCount", followingCount);

        // Get total tweet count the logged in user
        List<Tweet> userTweets = tweetService.getTweetsForUserById(user_id);
        List<Tweet> userRetweets = tweetService.getRetweetsForUserById(user_id);
        int totalTweets = userTweets.size() + userRetweets.size();
        model.addAttribute("totalTweets", totalTweets);



        List<Tweet> tweetsList = tweetService.getFeed();

        List<UserTweet> feedTweets = new ArrayList<>();

        for (Tweet t : tweetsList) {

            User user = userService.getUserById(t.getUser_id());


            if (t.getNewTweet() != null) {

                User user2 = userService.getUserById(t.getNewTweet().getUser_id());

                UserTweet userTweet2 = new UserTweet(t.getNewTweet().getId(),user2.getFirstName(), user2.getLastName(), user2.getUserName(), user2.getProfileImageName(), t.getNewTweet().getDateTime(), t.getNewTweet().getImageName(), t.getNewTweet().getMessage());

                UserTweet userTweet = new UserTweet(t.getId(),user.getFirstName(),user.getLastName(), user.getUserName(), user.getProfileImageName(), t.getDateTime(), t.getImageName(), t.getMessage(), userTweet2);

                feedTweets.add(userTweet);

            } else {
                UserTweet userTweet = new UserTweet(t.getId(),user.getFirstName(),user.getLastName(), user.getUserName(), user.getProfileImageName(), t.getDateTime(), t.getImageName(), t.getMessage());

                feedTweets.add(userTweet);
            }



        }

        model.addAttribute("feedTweets", feedTweets);
        return "home";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
}
