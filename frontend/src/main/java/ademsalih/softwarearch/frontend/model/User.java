package ademsalih.softwarearch.frontend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private long user_id;

    private String name;

    private String email;

    private String userName;

    private String password;

    private String phone;

    private String accountCreated;

    private String profileImageName;

    private String userRole;

    private String bio;

    private String bannerImageName;

    private String link;

    private String location;

    public User(String name, String email, String phone, String userName, String password, String accountCreated, String profileImageName, String userRole, String bio, String bannerImageName, String link, String location) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.accountCreated = accountCreated;
        this.profileImageName = profileImageName;
        this.userRole = userRole;
        this.bio = bio;
        this.bannerImageName = bannerImageName;
        this.link = link;
        this.location = location;
    }
}
