package ademsalih.softwarearch.userservice.controller;

import ademsalih.softwarearch.userservice.model.User;
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
    @GetMapping("/users/{id}/followers")
    public List<User> getFollowers(@PathVariable long id) {
        return userService.getFollowersForUser(id);
    }

    // OK
    @GetMapping("/users/{id}/followings")
    public List<User> getFollowings(@PathVariable long id) {
        return userService.getFollowingsForUser(id);
    }
    

    // TODO: DELETE /users/{user_id}/following/{following_id}
    // TODO: DELETE /users/{user_id}/followers/{follower_id}

    // TODO: POST /users/{user_id}/following/{following_id}
    // TODO: POST /users/{user_id}/followers/{following_id}

}
