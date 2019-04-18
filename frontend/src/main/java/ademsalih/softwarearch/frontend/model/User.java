package ademsalih.softwarearch.frontend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private long user_id;
    private String firstName;
    private String lastName;

    @NotNull
    private String email;
    private String phone;
    private String userName;

    @NotNull
    private String password;
    private String accountCreated;
    private String profileImageName;
    private String userRole;

    @Size(max = 160)
    private String bio;
    private String bannerImageName;
    private String link;
    private String city;
    private String country;

    public User(String firstName, String lastName, String email, String phone, String userName,
                String password, String accountCreated, String profileImageName, String userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.accountCreated = accountCreated;
        this.profileImageName = profileImageName;
        this.userRole = userRole;
    }

}
