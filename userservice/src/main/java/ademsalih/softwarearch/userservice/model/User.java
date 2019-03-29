package ademsalih.softwarearch.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String accountCreated;
    private String profileImageName;
    private String userRole;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"following", "followers"})
    @JoinTable(
            name = "Following",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "following_user_id") }
    )
    private Set<User> following = new HashSet<>();


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "following")
    @JsonIgnoreProperties({"following", "followers"})
    private Set<User> followers = new HashSet<>();

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
