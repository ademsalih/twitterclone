package ademsalih.softwarearch.frontend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Follow {

    private long id;

    private User user;

    public User following_user;

    public Follow(User user, User following_user) {
        this.user = user;
        this.following_user = following_user;
    }

}
