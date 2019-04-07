package ademsalih.softwarearch.tweetservice;

import ademsalih.softwarearch.tweetservice.model.NewTweet;
import ademsalih.softwarearch.tweetservice.model.Retweet;
import ademsalih.softwarearch.tweetservice.repository.RetweetRepository;
import ademsalih.softwarearch.tweetservice.repository.NewTweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

        NewTweet newTweet1 = new NewTweet("My first newTweet!", "02.04.2019 13:52", 1,"image1.jpg");
        NewTweet newTweet2 = new NewTweet("My second newTweet! Lorem ipsum dolor sit amet.", "02.04.2019 13:52", 1,"image2.jpg");
        newTweetRepository.save(newTweet1);
        newTweetRepository.save(newTweet2);

        Retweet retweet = new Retweet(2, "02.04.2019 14:26", newTweet1);
        retweetRepository.save(retweet);




    }
}
