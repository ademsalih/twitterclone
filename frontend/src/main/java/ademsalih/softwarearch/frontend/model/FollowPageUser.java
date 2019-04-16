package ademsalih.softwarearch.frontend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FollowPageUser {

    private long user_id;
    private String firstName;
    private String lastName;
    private String userName;
    private String profileImageName;
    private String bannerImageName;
    private String bio;
    private boolean followable;


    public FollowPageUser(long user_id, String firstName, String lastName, String userName, String profileImageName,
                          String bannerImageName, String bio, boolean followable) {
        this.user_id = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.profileImageName = profileImageName;
        this.bannerImageName = bannerImageName;
        this.bio = bio;
        this.followable = followable;
    }
}
