package ademsalih.softwarearch.tweetservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Indexed
public class NewTweet extends Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long user;

    private Calendar dateTime;

    private String imageName;

    @Field(index = Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String message;

    @OneToMany(mappedBy = "newTweet", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Retweet> retweets = new ArrayList<>();

    public NewTweet(String message, Calendar dateTime, long user, String imageName) {
        this.message = message;
        this.dateTime = dateTime;
        this.user = user;
        this.imageName = imageName;
    }

}
