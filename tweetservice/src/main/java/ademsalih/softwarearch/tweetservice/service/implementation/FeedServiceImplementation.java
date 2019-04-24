package ademsalih.softwarearch.tweetservice.service.implementation;

import ademsalih.softwarearch.tweetservice.model.NewTweet;
import ademsalih.softwarearch.tweetservice.model.Retweet;
import ademsalih.softwarearch.tweetservice.model.Tweet;
import ademsalih.softwarearch.tweetservice.repository.RetweetRepository;
import ademsalih.softwarearch.tweetservice.repository.NewTweetRepository;
import ademsalih.softwarearch.tweetservice.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedServiceImplementation implements FeedService {

    @Autowired
    NewTweetRepository newTweetRepository;

    @Autowired
    RetweetRepository retweetRepository;

    public List<Tweet> getFeed() {

        List<NewTweet> newTweets = newTweetRepository.findAll();
        List<Retweet> retweets = retweetRepository.findAll();

        List<Tweet> feed = new ArrayList<>();

        feed.addAll(newTweets);
        feed.addAll(retweets);

        return feed;
    }

    @Override
    public List<Tweet> getFeedForUser(long id) {

        List<Tweet> feed = new ArrayList<>();
        return null;
    }
}
