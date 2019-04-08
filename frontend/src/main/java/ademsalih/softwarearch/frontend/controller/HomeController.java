package ademsalih.softwarearch.frontend.controller;

import ademsalih.softwarearch.frontend.model.Follow;
import ademsalih.softwarearch.frontend.model.Tweet;
import ademsalih.softwarearch.frontend.model.User;
import ademsalih.softwarearch.frontend.model.UserTweet;
import ademsalih.softwarearch.frontend.service.FollowService;
import ademsalih.softwarearch.frontend.service.TweetService;
import ademsalih.softwarearch.frontend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String tweet(@ModelAttribute("tweet") Tweet tweet) {

        tweet.setUser_id(user_id);
        tweet.setNewTweet(null);
        tweet.setDateTime("now");
        tweet.setImageName("asdas.jpg");

        tweetService.postTweet(tweet);

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {

        model.addAttribute("tweet", new Tweet());

        List<Follow> followers = followService.getFollowersForUserById(user_id);
        int followerCount = followers.size();
        model.addAttribute("followerCount", followerCount);

        List<Follow> following = followService.getFollowingsForUserById(user_id);
        int followingCount = following.size();
        model.addAttribute("followingCount", followingCount);


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

                UserTweet userTweet2 = new UserTweet(user2.getFirstName(), user2.getLastName(), user2.getUserName(), user2.getProfileImageName(), t.getNewTweet().getDateTime(), t.getNewTweet().getImageName(), t.getNewTweet().getMessage());

                UserTweet userTweet = new UserTweet(user.getFirstName(),user.getLastName(), user.getUserName(), user.getProfileImageName(), t.getDateTime(), t.getImageName(), t.getMessage(), userTweet2);

                feedTweets.add(userTweet);

            } else {
                UserTweet userTweet = new UserTweet(user.getFirstName(),user.getLastName(), user.getUserName(), user.getProfileImageName(), t.getDateTime(), t.getImageName(), t.getMessage());

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
