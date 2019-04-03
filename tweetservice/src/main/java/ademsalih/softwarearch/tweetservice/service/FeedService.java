package ademsalih.softwarearch.tweetservice.service;

import ademsalih.softwarearch.tweetservice.model.TweetAbstract;

import java.util.List;

public interface FeedService {

    List<TweetAbstract> getFeed();

    List<TweetAbstract> getFeedForUser(long id);
}
