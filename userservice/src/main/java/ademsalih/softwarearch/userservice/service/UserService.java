package ademsalih.softwarearch.userservice.service;


import ademsalih.softwarearch.userservice.model.User;
import ademsalih.softwarearch.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public interface UserService {

    Set<User> getUsers();

    User getUserById(long id);

}
