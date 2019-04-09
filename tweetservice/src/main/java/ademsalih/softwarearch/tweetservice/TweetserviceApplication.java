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

        NewTweet newTweet1 = new NewTweet("My first newTweet!", "02.04.2019 13:52", 1,null);
        NewTweet newTweet3 = new NewTweet("Jeg elsker is!", "07.04.2019 22:06", 2,null);

        NewTweet newTweet4 = new NewTweet("Dette er min aller første tweet. Hva er en tweet egentlig? Nå logger jeg ut...", "08.04.2019 18:18", 3,null);


        newTweetRepository.save(newTweet1);
        newTweetRepository.save(newTweet3);
        newTweetRepository.save(newTweet4);

        Retweet retweet = new Retweet(2, "02.04.2019 14:26", newTweet4);
        retweetRepository.save(retweet);




    }
}
