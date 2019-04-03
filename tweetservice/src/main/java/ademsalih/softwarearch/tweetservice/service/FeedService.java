package ademsalih.softwarearch.tweetservice.service;

import ademsalih.softwarearch.tweetservice.model.Tweet;

import java.util.List;

public interface FeedService {

    List<Tweet> getFeed();

    List<Tweet> getFeedForUser(long id);
}
