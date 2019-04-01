package ademsalih.softwarearch.userservice.service;


import ademsalih.softwarearch.userservice.model.User;
import ademsalih.softwarearch.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public interface UserService {

    Set<User> getUsers();

    User getUserById(long id);

    User saveUser(User user);

    List<User> getAllUsers();

    void deleteUser(long id);

    List<User> getFollowersForUser(long id);

    List<User> getFollowingsForUser(long id);

}
