package ademsalih.softwarearch.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user")
    @JsonIgnoreProperties({"userRole", "profileImageName", "accountCreated", "password", "userName", "phone", "email", "lastName", "firstName", "country", "city", "link", "bannerImageName", "bio"})
    private User user;

    @ManyToOne
    @JoinColumn(name="following_user")
    @JsonIgnoreProperties({"userRole", "profileImageName", "accountCreated", "password", "userName", "phone", "email", "lastName", "firstName", "country", "city", "link", "bannerImageName", "bio"})
    private User following_user;

    public Follow(User user, User following_user) {
        this.user = user;
        this.following_user = following_user;
    }

}
