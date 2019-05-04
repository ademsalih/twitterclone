package ademsalih.softwarearch.userservice.service;


import ademsalih.softwarearch.userservice.model.Follow;
import ademsalih.softwarearch.userservice.model.FollowStatus;
import ademsalih.softwarearch.userservice.model.User;
import ademsalih.softwarearch.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    User getUserById(long id);

    User saveUser(User user);

    List<User> getAllUsers();

    void deleteUser(long id);

    List<Follow> getFollowersForUser(long id);

    List<Follow> getFollowingsForUser(long id);

    void deleteFollowing(long user, long following);

    FollowStatus checkIfIsFollowing(long user, long following);

    User findUserByUserName(String userName);

    User findUserByEmail(String email);

    List<User> getFollowSuggestionsFor(long id);

}
