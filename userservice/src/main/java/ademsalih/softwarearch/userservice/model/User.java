package ademsalih.softwarearch.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    private String name;

    private String email;

    private String phone;

    private String userName;

    private String password;

    private Calendar accountCreated;

    private String profileImageName;

    private String userRole;

    private String bio;

    private String bannerImageName;

    private String link;

    private String location;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Follow> following = new ArrayList<>();


    @OneToMany(mappedBy = "following_user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Follow> followers = new ArrayList<>();


    public User(String name, String email, String phone, String userName, String password, Calendar accountCreated, String profileImageName, String userRole, String bio, String bannerImageName, String link, String location) {
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
