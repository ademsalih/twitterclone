package ademsalih.softwarearch.frontend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private long user_id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    private String phone;

    @NotNull
    @Size(min = 1, max = 10)
    private String userName;

    @NotNull
    @Size(min = 5)
    private String password;

    private String accountCreated;

    private String profileImageName;

    private String userRole;

    @Size(max = 160)
    private String bio;

    private String bannerImageName;

    @Pattern( regexp = "http:\\/\\/www\\..+\\.\\w+" )
    private String link;
    private String location;

    public User(@NotNull String name, @NotNull String email, @NonNull String phone, @NotNull @Size(min = 1, max = 10) String userName, @NotNull @Size(min = 5) String password, String accountCreated, String profileImageName, String userRole, @Size(max = 160) String bio, String bannerImageName, String link, String location) {
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
