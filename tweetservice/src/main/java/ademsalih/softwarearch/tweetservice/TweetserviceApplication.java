package ademsalih.softwarearch.tweetservice;

import ademsalih.softwarearch.tweetservice.model.NewTweet;
import ademsalih.softwarearch.tweetservice.model.Retweet;
import ademsalih.softwarearch.tweetservice.repository.RetweetRepository;
import ademsalih.softwarearch.tweetservice.repository.NewTweetRepository;
import ademsalih.softwarearch.tweetservice.tools.CalendarFromEpoch;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
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

        NewTweet adem1 = new NewTweet(
                "My name is Adem and this is my first ever tweet.",
                new CalendarFromEpoch(1158750182).get(),
                2,
                null
        );

        NewTweet adem2 = new NewTweet(
                "I made this website.",
                new CalendarFromEpoch(1159009382).get(),
                2,
                null
        );





        NewTweet jens1 = new NewTweet(
                "Today we mark the #WorldPressFreedomDay. #NATO is an Alliance of values & freedom of the media is an important part of democracy.",
                new CalendarFromEpoch(1556972912).get(),
                3,
                "jensstoltenberg-1.jpg"
        );

        NewTweet jens2 = new NewTweet(
                "A warm welcome to General Tod Wolters as #NATO’s new Supreme Allied Commander Europe.",
                new CalendarFromEpoch(1556882912).get(),
                3,
                "jensstoltenberg-2.jpg"
        );

        NewTweet jens3 = new NewTweet(
                "Just spoke with Ukraine’s President-elect Volodymyr Zelensky. I congratulated him on his victory & invited him to visit #NATO HQ in the near future.",
                new CalendarFromEpoch(1549394912).get(),
                3,
                null
        );





        NewTweet erna1 = new NewTweet(
                "The world is only as strong as its most vulnerable citizen. As a community, we need to ensure everyone has access to vaccines and that no-one opts out. #VaccinesWork",
                new CalendarFromEpoch(1557059405).get(),
                4,
                "erna-1.jpg"
        );

        NewTweet erna2 = new NewTweet(
        "Congratulations to Volodymyr Zelenskiy, Ukraine’s new President. Norway will continue to support Ukraine’s Euro-Atlantic choice, the reform programme, Ukrainian sovereignty and territorial integrity.",
                new CalendarFromEpoch(1557055818).get(),
                4,
                null
        );

        NewTweet erna3 = new NewTweet(
                "I strongly condemn the senseless and horrible attacks on churches and hotels in #SriLanka this Easter morning. My thoughts are with the victims and their families.",
                new CalendarFromEpoch(1556796618).get(),
                4,
                null
        );







        NewTweet don1 = new NewTweet(
                "How can it be possible that James Woods (and many others), a strong but responsible Conservative Voice, is banned from Twitter? Social Media & Fake News Media.",
                new CalendarFromEpoch(1552655186).get(),
                5,
                null
        );

        NewTweet don2 = new NewTweet(
                "Very good call yesterday with President Putin of Russia. Tremendous potential for a good/great relationship with Russia, despite what you read and see in the Fake News Media.",
                new CalendarFromEpoch(1556998417).get(),
                5,
                null
        );

        NewTweet don3 = new NewTweet(
                "Anything in this very interesting world is possible, but I believe that Kim Jong Un fully realizes the great economic potential of North Korea, & will do nothing to interfere or end it.",
                new CalendarFromEpoch(1556627617).get(),
                5,
                null
        );

        NewTweet don4 = new NewTweet(
                "Today, May 4th - is International Firefighters Day! We remember all of those who put their lives on the line to save others, and are grateful to those who continue to do so 24/7/365. We love our great firefighters.",
                new CalendarFromEpoch(1556975186).get(),
                5,
                "don-1.jpeg"
        );

        NewTweet don5 = new NewTweet(
                "Despite the constant negative press covfefe",
                new CalendarFromEpoch(1496232378).get(),
                5,
                null
        );

        NewTweet don6 = new NewTweet(
                "Who can figure out the real meaning of \"covfefe\" ??? Enjoy!",
                new CalendarFromEpoch(1496232378).get(),
                5,
                null
        );





        NewTweet obama1 = new NewTweet(
                "Condolences to the family of John Singleton. His seminal work, Boyz n the Hood, remains one of the most searing, loving portrayals of the challenges facing inner-city youth.",
                new CalendarFromEpoch(1548938017).get(),
                6,
                null
        );


        NewTweet obama2 = new NewTweet(
                "For thirty-six years, Richard Lugar proved that pragmatism and decency work—not only in Washington but all over the world. Michelle and I send our warmest sympathies to his family and all those who had the privilege of knowing this American statesman.",
                new CalendarFromEpoch(1480854817).get(),
                6,
                "obama-1.jpeg"
        );


        NewTweet obama3 = new NewTweet(
                "This generation of climate activists is tired of inaction, and they've caught the attention of leaders all over the world. So while this challenge is only getting more urgent, they show us the kind of action it'll take to meet this moment.",
                new CalendarFromEpoch(1459514017).get(),
                6,
                null
        );

        Retweet retweet = new Retweet(
                6,
                new CalendarFromEpoch(1557060086).get(),
                don5
        );


        newTweetRepository.save(adem1);
        newTweetRepository.save(adem2);

        newTweetRepository.save(jens1);
        newTweetRepository.save(jens2);
        newTweetRepository.save(jens3);

        newTweetRepository.save(erna1);
        newTweetRepository.save(erna2);
        newTweetRepository.save(erna3);

        newTweetRepository.save(don1);
        newTweetRepository.save(don2);
        newTweetRepository.save(don3);
        newTweetRepository.save(don4);
        newTweetRepository.save(don5);
        newTweetRepository.save(don6);

        newTweetRepository.save(obama1);
        newTweetRepository.save(obama2);
        newTweetRepository.save(obama3);



        retweetRepository.save(retweet);

    }
}
