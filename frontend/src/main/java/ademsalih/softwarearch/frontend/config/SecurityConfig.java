package ademsalih.softwarearch.frontend.config;

import ademsalih.softwarearch.frontend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginService loginService;

    /*private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
        //authBuilder.userDetailsService(loginService).passwordEncoder(bCryptPasswordEncoder);
        authBuilder.userDetailsService(loginService);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable();

        String[] resources = new String[]{
                "/",
                "/signup",
                "/h2-console/**",
                "/register",
                "/pictureCheckCode",
                "/include/**",
                "/css/**",
                "/images/**",
                "/js/**",
                "/static/**"
        };


        http.authorizeRequests()
                .antMatchers(resources)
                .permitAll()

                .antMatchers("/add","/remove/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .permitAll();
    }


}