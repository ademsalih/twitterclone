package ademsalih.softwarearch.userservice.controller;

import ademsalih.softwarearch.userservice.model.User;
import ademsalih.softwarearch.userservice.service.UserService;
import ademsalih.softwarearch.userservice.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users/{id}")
    public User getUserDetails(@PathVariable long id) {
        return userService.getUserById(id);
    }


    // TODO: POST /users
    // TODO: GET /users [ADMIN]

    // TODO: PUT /users/{id}
    // TODO: DELETE /users/{id}

    // TODO: GET /users/{id}/following
    // TODO: GET /users/{id}/followers

    // TODO: DELETE /users/{id}/following/{id}
    // TODO: DELETE /users/{id}/followers/{id}

    // TODO: POST /users/{id}/following/{id}
    // TODO: POST /users/{id}/followers/{id}

}
