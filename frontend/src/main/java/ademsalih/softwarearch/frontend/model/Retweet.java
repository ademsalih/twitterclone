package ademsalih.softwarearch.frontend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
public class Retweet {

    private long rid;

    private long user_id;

    private Calendar dateTime;

    private Tweet newTweet;

    public Retweet(long user_id, Calendar dateTime, Tweet newTweet) {
        this.user_id = user_id;
        this.dateTime = dateTime;
        this.newTweet = newTweet;
    }
}
