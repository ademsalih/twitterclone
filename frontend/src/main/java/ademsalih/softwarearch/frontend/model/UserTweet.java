package ademsalih.softwarearch.frontend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserTweet {

    private String firstName;

    private String lastName;

    private String userName;

    private String profileImageName;

    private String dateTime;

    private String imageName;

    private String message;

    private UserTweet newTweet;

    public UserTweet(String firstName, String lastName, String userName, String profileImageName, String dateTime, String imageName, String message) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.profileImageName = profileImageName;
        this.dateTime = dateTime;
        this.imageName = imageName;
        this.message = message;
    }

    public UserTweet(String firstName, String lastName, String userName, String profileImageName, String dateTime, String imageName, String message, UserTweet newTweet) {
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
