package ademsalih.softwarearch.userservice.service;

import ademsalih.softwarearch.userservice.model.Follow;
import ademsalih.softwarearch.userservice.model.FollowStatus;
import ademsalih.softwarearch.userservice.model.User;
import ademsalih.softwarearch.userservice.model.UserRoles;
import ademsalih.softwarearch.userservice.repository.FollowRepository;
import ademsalih.softwarearch.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowRepository followRepository;

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
    public List<Follow> getFollowersForUser(long id) {
        User user = userRepository.findById(id).get();
        return user.getFollowers();
    }

    @Override
    public List<Follow> getFollowingsForUser(long id) {
        User user = userRepository.findById(id).get();
        return user.getFollowing();
    }

    @Override
    public void deleteFollowing(long user_id, long following_id) {
        List<Follow> follow = followRepository.findAll();
        for (Follow f : follow) {
            if (f.getUser().getUser_id() == user_id && f.getFollowing_user().getUser_id() == following_id) {
                followRepository.delete(f);
            }
        }
    }

    @Override
    public FollowStatus checkIfIsFollowing(long user, long following) {

        List<Follow> follows = followRepository.findAll();

        for (Follow f : follows) {
            if (f.getUser().getUser_id() == user && f.getFollowing_user().getUser_id() == following) {
                return new FollowStatus(true);
            }
        }

        return new FollowStatus(false);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByUserName(String userName) {

        User user = null;

        if (userRepository.findUserByUserName(userName).isPresent()) {
            user = userRepository.findUserByUserName(userName).get();
        }

        return user;
    }

}
