package ademsalih.softwarearch.frontend.service;

import ademsalih.softwarearch.frontend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    UserService userService;

    /*private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }*/

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userService.getUserByUsername(userName);

        if (user == null)
            throw new UsernameNotFoundException("Could not find user with userName: " + userName);

        return getUserDetails(user);
    }

    private UserDetails getUserDetails(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password("{noop}" + user.getPassword())
                .roles(user.getUserRole())
                .build();
    }

    /*public String encodePassword(String input) {
        return bCryptPasswordEncoder.encode(input);
    }*/

}
