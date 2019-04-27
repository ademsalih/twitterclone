package ademsalih.softwarearch.frontend.controller;

import ademsalih.softwarearch.frontend.model.User;
import ademsalih.softwarearch.frontend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired UserService userService;

    @GetMapping("/admin")
    public String admin(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        List<User> allUsers = userService.getUsers();

        model.addAttribute("users", allUsers);
        model.addAttribute("adminName", authUser.getName());

        return "admin-page";
    }

    /*@GetMapping()
    public String deleteUser() {
        return "";
    }

    @GetMapping()
    public String updatePassword() {
        return "";
    }*/

}
