package ademsalih.softwarearch.tweetservice.controller;

import ademsalih.softwarearch.tweetservice.model.NewTweet;
import ademsalih.softwarearch.tweetservice.model.Retweet;
import ademsalih.softwarearch.tweetservice.model.Tweet;
import ademsalih.softwarearch.tweetservice.service.FeedService;
import ademsalih.softwarearch.tweetservice.service.TweetSearchService;
import ademsalih.softwarearch.tweetservice.service.RetweetService;
import ademsalih.softwarearch.tweetservice.service.NewTweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TweetController {

    @Autowired
    private TweetSearchService searchservice;

    @Autowired
    NewTweetService newTweetService;

    @Autowired
    RetweetService retweetService;

    @Autowired
    FeedService feedService;

    @GetMapping("/feed")
    public List<Tweet> getFeed() {
        return feedService.getFeed();
    }

    @GetMapping("/newtweets")
    public List<NewTweet> getAllNewTweets() {
        return newTweetService.getAllNewTweets();
    }

    @PostMapping("/newtweets")
    public NewTweet saveNewTweet(@RequestBody NewTweet newTweet) {
        return newTweetService.saveNewTweet(newTweet);
    }

    @DeleteMapping("/newtweets/{id}")
    public void deleteNewTweet(@PathVariable long id) {
        newTweetService.deleteNewTweet(id);
    }

    @DeleteMapping("/newtweets/all/{id}")
    public void deleteAllNewTweets(@PathVariable long id) {
        newTweetService.deleteAllTweetsForUser(id);
    }

    @GetMapping("/newtweets/{id}")
    public NewTweet getNewTweet(@PathVariable long id) {
        return newTweetService.getNewTweet(id);
    }

    @GetMapping("/newtweets/user/{id}")
    public List<NewTweet> getNewTweetsForUser(@PathVariable long id) {
        return newTweetService.getNewTweetsForUser(id);
    }

    @GetMapping("/retweets")
    public List<Retweet> getAllRetweets() {
        return retweetService.getAllRetweets();
    }

    @PostMapping("/retweets")
    public Retweet saveRetweet(@RequestBody Retweet retweet) {
        return retweetService.saveRetweet(retweet);
    }

    @GetMapping("/retweets/{id}")
    public Retweet getRetweet(@PathVariable long id) {
        return retweetService.getRetweet(id);
    }

    @DeleteMapping("/retweets/{id}")
    public void deleteRetweet(@PathVariable long id) {
        retweetService.deleteRetweet(id);
    }

    @GetMapping("/retweets/user/{id}")
    public List<Retweet> getAllRetweets(@PathVariable long id) {
        return retweetService.getRetweetsForUser(id);
    }

    @GetMapping("/search")
    public List<NewTweet> searchTweets(@RequestParam("query") String query) {

        List<NewTweet> searchResults = new ArrayList<>();

        try {
            searchResults = searchservice.search(query);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return searchResults;
    }


}
