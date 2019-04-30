package ademsalih.softwarearch.userservice.controller;

import ademsalih.softwarearch.userservice.model.Follow;
import ademsalih.softwarearch.userservice.model.FollowStatus;
import ademsalih.softwarearch.userservice.model.User;
import ademsalih.softwarearch.userservice.service.FollowService;
import ademsalih.softwarearch.userservice.service.UserService;
import ademsalih.softwarearch.userservice.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FollowService followService;

    /** ----------------------- USER ------------------------ **/

    // OK
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // OK
    @PostMapping("/users")
    public User registerNewUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // OK
    @GetMapping("/users/{id}")
    public User getUserDetails(@PathVariable long id) {
        return userService.getUserById(id);
    }


    @GetMapping("/users/username/{userName}")
    public User getUserByUserName(@PathVariable String userName) {
        return userService.findUserByUserName(userName);
    }

    @GetMapping("/users/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }



    // OK
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id) {
        user.setUser_id(id);
        return userService.saveUser(user);
    }

    // OK
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    /** ----------------------- FOLLOWERS ------------------------ **/

    // OK
    @GetMapping("/followers/{id}")
    public List<Follow> getFollowers(@PathVariable long id) {
        return userService.getFollowersForUser(id);
    }

    // OK
    @GetMapping("/followings/{id}")
    public List<Follow> getFollowings(@PathVariable long id) {
        return userService.getFollowingsForUser(id);
    }

    // OK
    @PostMapping("/following")
    public void addFollowing(@RequestBody Follow follow) {
        followService.saveFollowing(follow);
    }

    // OK
    @DeleteMapping("/following/{user_id}/{following_id}")
    public void deleteFollowing(@PathVariable("user_id") long userId, @PathVariable("following_id") long followingId) {
        userService.deleteFollowing(userId, followingId);
    }

    @GetMapping("/following/{user_id}/{following_id}")
    public FollowStatus isFollowing(@PathVariable("user_id") long userId, @PathVariable("following_id") long followingId) {
        return userService.checkIfIsFollowing(userId, followingId);
    }




}