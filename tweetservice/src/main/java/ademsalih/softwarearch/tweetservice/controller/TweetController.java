package ademsalih.softwarearch.tweetservice.controller;

import ademsalih.softwarearch.tweetservice.model.Retweet;
import ademsalih.softwarearch.tweetservice.model.Tweet;
import ademsalih.softwarearch.tweetservice.model.TweetAbstract;
import ademsalih.softwarearch.tweetservice.service.FeedService;
import ademsalih.softwarearch.tweetservice.service.RetweetService;
import ademsalih.softwarearch.tweetservice.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TweetController {

    @Autowired
    TweetService tweetService;

    @Autowired
    RetweetService retweetService;

    @Autowired
    FeedService feedService;

    @GetMapping("/feed")
    public List<TweetAbstract> getFeed() {
        return feedService.getFeed();
    }

    @GetMapping("/tweets")
    public List<Tweet> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GetMapping("/tweets/{id}")
    public Tweet getTweet(@PathVariable long id) {
        return tweetService.getTweet(id);
    }

    @GetMapping("/tweets/user/{id}")
    public List<Tweet> getTweetsForUser(@PathVariable long id) {
        return tweetService.getTweetsForUser(id);
    }

    @GetMapping("/retweets")
    public List<Retweet> getAllRetweets() {
        return retweetService.getAllRetweets();
    }

    @GetMapping("/retweets/{id}")
    public Retweet getRetweet(@PathVariable long id) {
        return retweetService.getRetweet(id);
    }

    @GetMapping("/retweets/user/{id}")
    public List<Retweet> getAllRetweets(@PathVariable long id) {
        return retweetService.getRetweetsForUser(id);
    }


}
