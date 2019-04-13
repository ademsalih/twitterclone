package ademsalih.softwarearch.frontend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserTweet {

    private long tweet_id;

    private String firstName;

    private String lastName;

    private String userName;

    private String profileImageName;

    private String dateTime;

    private String imageName;

    private String message;

    private UserTweet newTweet;

    public UserTweet(long id, String firstName, String lastName, String userName, String profileImageName, String dateTime, String imageName, String message) {
        this.tweet_id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.profileImageName = profileImageName;
        this.dateTime = dateTime;
        this.imageName = imageName;
        this.message = message;
    }

    public UserTweet(long id, String firstName, String lastName, String userName, String profileImageName, String dateTime, String imageName, String message, UserTweet newTweet) {
        this.tweet_id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.profileImageName = profileImageName;
        this.dateTime = dateTime;
        this.imageName = imageName;
        this.message = message;
        this.newTweet = newTweet;
    }
}
