package ademsalih.softwarearch.tweetservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class NewTweet extends Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long user_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateTime;

    private String imageName;

    private String message;

    @OneToMany(mappedBy = "newTweet", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Retweet> retweets = new ArrayList<>();

    public NewTweet(String message, Calendar dateTime, long user_id, String imageName) {
        this.message = message;
        this.dateTime = dateTime;
        this.user_id = user_id;
        this.imageName = imageName;
    }

}
