package ademsalih.softwarearch.frontend.service;

import ademsalih.softwarearch.frontend.model.Retweet;
import ademsalih.softwarearch.frontend.model.Tweet;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetService {

    String BASE_URL = "http://localhost:9090/";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Tweet> getFeed(){
        return Arrays.stream(
                restTemplate.getForObject(BASE_URL + "feed", Tweet[].class)
        ).collect(Collectors.toList());
    }

    public List<Tweet> getTweetsForUserById(long id) {
        return Arrays.stream(
                restTemplate.getForObject(BASE_URL + "newtweets/user/" + id, Tweet[].class)
        ).collect(Collectors.toList());
    }

    public List<Tweet> getRetweetsForUserById(long id) {
        return Arrays.stream(
                restTemplate.getForObject(BASE_URL + "retweets/user/" + id, Tweet[].class)
        ).collect(Collectors.toList());
    }

    public Tweet postTweet(Tweet tweet) {
        return restTemplate.postForObject(BASE_URL + "newtweets", tweet, Tweet.class);
    }

    public Retweet postRetweet(Retweet retweet) {
        return restTemplate.postForObject(BASE_URL + "retweets", retweet, Retweet.class);
    }
}
