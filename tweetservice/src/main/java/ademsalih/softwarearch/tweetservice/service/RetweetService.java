package ademsalih.softwarearch.tweetservice.service;

import ademsalih.softwarearch.tweetservice.model.Retweet;

import java.util.List;

public interface RetweetService {

    List<Retweet> getAllRetweets();

    List<Retweet> getRetweetsForUser(long id);

    Retweet getRetweet(long id);
}
