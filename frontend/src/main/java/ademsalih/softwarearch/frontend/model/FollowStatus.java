package ademsalih.softwarearch.frontend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FollowStatus {

    private boolean follow;

    public FollowStatus(boolean follow) {
        this.follow = follow;
    }
}
