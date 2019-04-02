package ademsalih.softwarearch.tweetservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Retweet extends TweetAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long retweet_id;

    private long user_id;

    private String dateTime;

    @ManyToOne
    @JsonIgnoreProperties("retweets")
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    public Retweet(long user_id, String dateTime, Tweet tweet) {
        this.user_id = user_id;
        this.dateTime = dateTime;
        this.tweet = tweet;
    }
}
