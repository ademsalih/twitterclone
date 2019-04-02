package ademsalih.softwarearch.tweetservice;

import ademsalih.softwarearch.tweetservice.model.Retweet;
import ademsalih.softwarearch.tweetservice.model.Tweet;
import ademsalih.softwarearch.tweetservice.repository.RetweetRepository;
import ademsalih.softwarearch.tweetservice.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TweetserviceApplication implements CommandLineRunner {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    RetweetRepository retweetRepository;

    public static void main(String[] args) {
        SpringApplication.run(TweetserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        tweetRepository.deleteAllInBatch();
        retweetRepository.deleteAllInBatch();

        Tweet tweet1 = new Tweet("My first tweet!", "02.04.2019 13:52", 1,"image1.jpg");
        Tweet tweet2 = new Tweet("My second tweet!", "02.04.2019 13:52", 1,"image2.jpg");
        Tweet tweet3 = new Tweet("My third tweet!", "02.04.2019 13:52", 1,"image3.jpg");

        tweetRepository.save(tweet1);
        tweetRepository.save(tweet2);
        tweetRepository.save(tweet3);

        Retweet retweet = new Retweet(2, "02.04.2019 14:26", tweet1);
        retweetRepository.save(retweet);



    }
}
