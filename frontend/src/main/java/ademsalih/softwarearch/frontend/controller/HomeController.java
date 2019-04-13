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

import javax.servlet.ServletContext;
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

    @GetMapping({"/", "/login"})
    public String login() {
        return "login";
    }

    // TODO: ADD PRINCIPAL FOR USER ID

    @PostMapping("/tweet")
    public String tweet(@ModelAttribute("tweet") Tweet tweet, @RequestParam(value = "file", required=false) MultipartFile file, HttpServletRequest request) {

        tweet.setUser_id(user_id);
        tweet.setNewTweet(null);
        tweet.setDateTime(Calendar.getInstance());


        if (!file.isEmpty()) {
            try {
                String IMAGE_LOCATION = "/src/main/resources/static/images/";

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
    public String deleteTweet(@PathVariable long id) {
        tweetService.deleteTweet(id);
        return "redirect:/home";
    }

    @GetMapping("/deleteRetweet/{id}")
    public String deleteRetweet(@PathVariable long id) {
        tweetService.deleteRetweet(id);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {

        // Models for tweeting and retweeting
        model.addAttribute("tweet", new Tweet());
        model.addAttribute("retweet", new Retweet());
        model.addAttribute("loggedInUser", user_id);

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

        Collections.sort(tweetsList, Comparator.comparing(Tweet::getDateTime));

        Collections.reverse(tweetsList);

        List<UserTweet> feedTweets = new ArrayList<>();

        for (Tweet tweet : tweetsList) {

            User user = userService.getUserById(tweet.getUser_id());

            if (tweet.getNewTweet() != null) {

                User retweeter = userService.getUserById(tweet.getNewTweet().getUser_id());

                UserTweet retweet = new UserTweet(
                        tweet.getNewTweet().getId(),
                        retweeter.getFirstName(),
                        retweeter.getLastName(),
                        retweeter.getUserName(),
                        retweeter.getProfileImageName(),
                        new TimeFormatService().format(tweet.getNewTweet().getDateTime()),
                        tweet.getNewTweet().getImageName(),
                        tweet.getNewTweet().getMessage()
                );

                UserTweet originalTweet = new UserTweet(
                        tweet.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUserName(),
                        user.getProfileImageName(),
                        new TimeFormatService().format(tweet.getDateTime()),
                        tweet.getImageName(),
                        tweet.getMessage(),
                        retweet
                );

                feedTweets.add(originalTweet);

            } else {
                UserTweet userTweet = new UserTweet(
                        tweet.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUserName(),
                        user.getProfileImageName(),
                        new TimeFormatService().format(tweet.getDateTime()),
                        tweet.getImageName(),
                        tweet.getMessage()
                );

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
