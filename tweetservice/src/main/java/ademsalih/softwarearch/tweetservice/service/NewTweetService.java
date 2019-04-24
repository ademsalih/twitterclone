package ademsalih.softwarearch.tweetservice.service;

import ademsalih.softwarearch.tweetservice.model.NewTweet;
import ademsalih.softwarearch.tweetservice.model.Tweet;

import java.util.List;

public interface NewTweetService {

    List<NewTweet> getAllNewTweets();

    List<NewTweet> getNewTweetsForUser(long id);

    NewTweet getNewTweet(long id);

    NewTweet saveNewTweet(NewTweet newTweet);

    void deleteNewTweet(long id);

    List<NewTweet> getTweetForSearch(String query);
}
