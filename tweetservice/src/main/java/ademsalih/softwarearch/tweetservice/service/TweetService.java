package ademsalih.softwarearch.tweetservice.service;

import ademsalih.softwarearch.tweetservice.model.Tweet;

import java.util.List;

public interface TweetService {

    List<Tweet> getAllTweets();

    List<Tweet> getTweetsForUser(long id);

    Tweet getTweet(long id);
}
