package ademsalih.softwarearch.tweetservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tweet_id;

    private String message;

    private String dateTime;

    private long user_id;

    private String imageName;

    @OneToMany(mappedBy = "tweet")
    private List<Retweet> reshares = new ArrayList<>();

    public Tweet(String message, String dateTime, long user_id, String imageName) {
        this.message = message;
        this.dateTime = dateTime;
        this.user_id = user_id;
        this.imageName = imageName;
    }

}
