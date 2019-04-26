package ademsalih.softwarearch.tweetservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Retweet extends Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long user;

    private Calendar dateTime;

    @ManyToOne()
    @JsonIgnoreProperties("retweets")
    @JoinColumn(name = "tweet_id")
    private NewTweet newTweet;

    public Retweet(long user, Calendar dateTime, NewTweet newTweet) {
        this.user = user;
        this.dateTime = dateTime;
        this.newTweet = newTweet;
    }
}
