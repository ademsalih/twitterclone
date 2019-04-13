package ademsalih.softwarearch.frontend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Comparator;

@NoArgsConstructor
@Getter
@Setter
public class Tweet implements Comparator {

    private long id;

    private long user_id;

    protected Calendar dateTime;

    private String imageName;

    private String message;

    private Tweet newTweet;

    public Tweet(long user_id, Calendar dateTime, String imageName, String message, Tweet newTweet) {
        this.user_id = user_id;
        this.dateTime = dateTime;
        this.imageName = imageName;
        this.message = message;
        this.newTweet = newTweet;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}
