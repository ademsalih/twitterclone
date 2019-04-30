package ademsalih.softwarearch.frontend.controller;

import ademsalih.softwarearch.frontend.model.User;
import ademsalih.softwarearch.frontend.service.TweetService;
import ademsalih.softwarearch.frontend.service.UserService;
import ademsalih.softwarearch.frontend.viewmodel.PasswordEditUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    TweetService tweetService;

    @GetMapping("/admin")
    public String admin(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authUser = userService.getUserByUsername(auth.getName());

        List<User> allUsers = userService.getUsers();

        model.addAttribute("users", allUsers);
        model.addAttribute("adminName", authUser.getName());

        return "admin-page";
    }

    @GetMapping("/deleteaccount/{id}")
    public String deleteUser(@PathVariable long id) {
        tweetService.deleteTweetsByUser(id);
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/updatepassword/{id}")
    public String updatePassword(PasswordEditUser passwordEditUser, Model model, @PathVariable long id) {

        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "admin-page-password";
    }

    @PostMapping("/passwordsave/{id}")
    public String updatePasswordSave(@PathVariable long id, @Valid PasswordEditUser user, BindingResult bindingResult, HttpServletRequest httpServletRequest, Model model) {

        User serverUser = userService.getUserById(id);

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", serverUser);
            return "admin-page-password";
        }

        serverUser.setPassword(user.getPassword());

        userService.updateUser(serverUser);
        return "redirect:/admin";
    }

}
