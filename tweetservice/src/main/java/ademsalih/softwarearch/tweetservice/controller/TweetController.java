package ademsalih.softwarearch.tweetservice.controller;

import ademsalih.softwarearch.tweetservice.model.Retweet;
import ademsalih.softwarearch.tweetservice.model.Tweet;
import ademsalih.softwarearch.tweetservice.model.TweetAbstract;
import ademsalih.softwarearch.tweetservice.service.FeedService;
import ademsalih.softwarearch.tweetservice.service.implementation.FeedServiceImplementation;
import ademsalih.softwarearch.tweetservice.service.RetweetService;
import ademsalih.softwarearch.tweetservice.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/tweets")
    public Tweet saveTweet(@RequestBody Tweet tweet) {
        return tweetService.saveTweet(tweet);
    }

    @DeleteMapping("/tweets/{id}")
    public void deleteTweet(@PathVariable long id) {
        tweetService.deleteTweet(id);
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
