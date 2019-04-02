package ademsalih.softwarearch.tweetservice.service;

import ademsalih.softwarearch.tweetservice.model.Tweet;
import ademsalih.softwarearch.tweetservice.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TweetServiceImplementation implements TweetService {

    @Autowired
    TweetRepository tweetRepository;

    @Override
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    @Override
    public List<Tweet> getTweetsForUser(long id) {
        List<Tweet> allTweets = tweetRepository.findAll();
        List<Tweet> userTweets = new ArrayList<>();

        for (Tweet t : allTweets) {
            if (t.getUser_id() == id) userTweets.add(t);
        }

        return userTweets;
    }

    @Override
    public Tweet getTweet(long id) {
        return tweetRepository.findById(id).get();
    }
}
