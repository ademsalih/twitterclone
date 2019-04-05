package ademsalih.softwarearch.frontend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Tweet {

    protected long id;

    protected long user_id;

    protected String dateTime;

    private String imageName;

    private String message;

    private Tweet newTweet;

    public Tweet(long user_id, String dateTime, String imageName, String message, Tweet newTweet) {
        this.user_id = user_id;
        this.dateTime = dateTime;
        this.imageName = imageName;
        this.message = message;
        this.newTweet = newTweet;
    }
}
