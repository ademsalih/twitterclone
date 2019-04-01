package ademsalih.softwarearch.userservice.service;

import ademsalih.softwarearch.userservice.model.User;
import ademsalih.softwarearch.userservice.model.UserRoles;
import ademsalih.softwarearch.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public User saveUser(User user) {
        user.setUserRole(UserRoles.USER.name());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);

    }

    @Override
    public List<User> getFollowersForUser(long id) {
        User user = userRepository.findById(id).get();
        List<User> followers = user.getFollowers();
        return followers;
    }

    @Override
    public List<User> getFollowingsForUser(long id) {
        User user = userRepository.findById(id).get();
        List<User> followings = user.getFollowing();
        return followings;
    }
}
