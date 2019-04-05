package ademsalih.softwarearch.tweetservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Retweet extends Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long user_id;

    private String dateTime;

    @ManyToOne
    @JsonIgnoreProperties("retweets")
    @JoinColumn(name = "tweet_id")
    private NewTweet newTweet;

    public Retweet(long user_id, String dateTime, NewTweet newTweet) {
        this.user_id = user_id;
        this.dateTime = dateTime;
        this.newTweet = newTweet;
    }
}
