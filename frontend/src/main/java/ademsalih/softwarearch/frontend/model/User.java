package ademsalih.softwarearch.frontend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private long user_id;

    @NotNull(message = "What is your name?")
    @Size(max = 50, message = "Max 50 characters")
    private String name;

    @NotNull(message = "Email required")
    @Email
    private String email;

    @NotNull(message = "Username required")
    @Size(max = 15, message = "Max 15 characters")
    private String userName;

    @Size(min = 6, message = "Too short")
    private String password;

    private String phone;

    private String accountCreated;

    private String profileImageName;

    private String userRole;

    @Size(max = 160)
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
