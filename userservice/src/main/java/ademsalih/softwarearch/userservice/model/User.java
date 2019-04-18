package ademsalih.softwarearch.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private String firstName;
    private String lastName;
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
    private String city;
    private String country;

    /* ----------------- Many to many without extra table ----------------- */

    /*@ManyToMany(cascade = CascadeType.DETACH)
    @JsonIgnoreProperties({"following", "followers"})
    @JsonIgnore
    @JoinTable(
            name = "Following",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "following_user_id") }
    )
    private List<User> following = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.DETACH, mappedBy = "following")
    @JsonIgnoreProperties({"following", "followers"})
    @JsonIgnore
    private List<User> followers = new ArrayList<>();*/


    /* ---------------- Many to many with table Following ---------------- */

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Follow> following = new ArrayList<>();


    @OneToMany(mappedBy = "following_user")
    @JsonIgnore
    private List<Follow> followers = new ArrayList<>();


    public User(String firstName, String lastName, String email, String phone, String userName, String password, Calendar accountCreated, String profileImageName, String userRole, String bio, String bannerImageName, String link, String city, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
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
        this.city = city;
        this.country = country;
    }

}
