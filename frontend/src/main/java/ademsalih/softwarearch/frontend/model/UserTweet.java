package ademsalih.softwarearch.frontend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserTweet {

    private long user_id;

    private long tweet_id;

    private String name;

    private String userName;

    private String profileImageName;

    private String dateTime;

    private String imageName;

    private String message;

    private UserTweet newTweet;

    public UserTweet(long user_id, long tweet_id, String name, String userName, String profileImageName, String dateTime, String imageName, String message) {
        this.user_id = user_id;
        this.tweet_id = tweet_id;
        this.name = name;
        this.userName = userName;
        this.profileImageName = profileImageName;
        this.dateTime = dateTime;
        this.imageName = imageName;
        this.message = message;
    }

    public UserTweet(long user_id, long tweet_id, String name, String userName, String profileImageName, String dateTime, String imageName, String message, UserTweet newTweet) {
        this.user_id = user_id;
        this.tweet_id = tweet_id;
        this.name = name;
        this.userName = userName;
        this.profileImageName = profileImageName;
        this.dateTime = dateTime;
        this.imageName = imageName;
        this.message = message;
        this.newTweet = newTweet;
    }
}
