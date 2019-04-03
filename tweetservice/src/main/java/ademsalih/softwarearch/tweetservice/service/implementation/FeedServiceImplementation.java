package ademsalih.softwarearch.tweetservice.service.implementation;

import ademsalih.softwarearch.tweetservice.model.Retweet;
import ademsalih.softwarearch.tweetservice.model.Tweet;
import ademsalih.softwarearch.tweetservice.model.TweetAbstract;
import ademsalih.softwarearch.tweetservice.repository.RetweetRepository;
import ademsalih.softwarearch.tweetservice.repository.TweetRepository;
import ademsalih.softwarearch.tweetservice.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedServiceImplementation implements FeedService {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    RetweetRepository retweetRepository;

    public List<TweetAbstract> getFeed() {

        List<Tweet> tweets = tweetRepository.findAll();
        List<Retweet> retweets = retweetRepository.findAll();

        List<TweetAbstract> feed = new ArrayList<>();

        feed.addAll(tweets);
        feed.addAll(retweets);

        return feed;
    }

    public List<TweetAbstract> getFeedForUser(long id) {

        List<TweetAbstract> feed = new ArrayList<>();
        return null;
    }
}
