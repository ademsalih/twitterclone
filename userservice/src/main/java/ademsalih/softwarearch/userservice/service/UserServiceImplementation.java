package ademsalih.softwarearch.userservice.service;

import ademsalih.softwarearch.userservice.model.User;
import ademsalih.softwarearch.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Set<User> getUsers() {
        return null;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }
}
