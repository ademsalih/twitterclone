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

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/feed")
    public String feed(Model model) {

        long user_id = 1;

        List<Follow> followers = followService.getFollowersForUserById(user_id);
        int followerCount = followers.size();
        model.addAttribute("followerCount", followerCount);

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
}
