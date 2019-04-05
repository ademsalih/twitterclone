package ademsalih.softwarearch.frontend.controller;

import ademsalih.softwarearch.frontend.model.Tweet;
import ademsalih.softwarearch.frontend.model.User;
import ademsalih.softwarearch.frontend.model.UserTweet;
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

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/feed")
    public String feed(Model model) {

        List<Tweet> tweetsList = tweetService.getFeed();

        List<UserTweet> feedTweets = new ArrayList<>();

        for (Tweet t : tweetsList) {

            User user = userService.getUserById(t.getUser_id());

            UserTweet userTweet = new UserTweet(user.getFirstName(),user.getLastName(), user.getUserName(), user.getProfileImageName(), t.getDateTime(), t.getImageName(), t.getMessage(), t.getNewTweet());

            feedTweets.add(userTweet);
        }

        model.addAttribute("feedTweets", feedTweets);
        return "home";
    }
}
