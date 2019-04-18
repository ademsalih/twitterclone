package ademsalih.softwarearch.tweetservice;

import ademsalih.softwarearch.tweetservice.model.NewTweet;
import ademsalih.softwarearch.tweetservice.model.Retweet;
import ademsalih.softwarearch.tweetservice.repository.RetweetRepository;
import ademsalih.softwarearch.tweetservice.repository.NewTweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.util.Calendar;

@SpringBootApplication
public class TweetserviceApplication implements CommandLineRunner {

    @Autowired
    NewTweetRepository newTweetRepository;

    @Autowired
    RetweetRepository retweetRepository;

    public static void main(String[] args) {
        SpringApplication.run(TweetserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        newTweetRepository.deleteAllInBatch();
        retweetRepository.deleteAllInBatch();

        NewTweet newTweet = new NewTweet("This a tweet for testing date time purposes.", Calendar.getInstance(),1,null);
        NewTweet newTweet2 = new NewTweet("This a tweet for testing deletion.", Calendar.getInstance(),3,null);
        NewTweet newTweet3 = new NewTweet("This a tweet for testing deletion. Like the other ones", Calendar.getInstance(),2,null);
        Retweet retweet = new Retweet(3, Calendar.getInstance(), newTweet);

        newTweetRepository.save(newTweet);
        newTweetRepository.save(newTweet2);
        newTweetRepository.save(newTweet3);
        retweetRepository.save(retweet);






    }
}
