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

    private long user;

    private Calendar dateTime;

    private Tweet newTweet;

    public Retweet(long user, Calendar dateTime, Tweet newTweet) {
        this.user = user;
        this.dateTime = dateTime;
        this.newTweet = newTweet;
    }
}
