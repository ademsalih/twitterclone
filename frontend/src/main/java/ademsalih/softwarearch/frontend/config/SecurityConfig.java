package ademsalih.softwarearch.frontend.config;

import ademsalih.softwarearch.frontend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginService loginService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.userDetailsService(loginService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new CustomAuthenticationSuccessHandler();
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
                "/assets/**",
                "/js/**",
                "/static/**",
                "/all"
        };

        String[] adminURLs = new String[]{
                "/admin",
                "/deleteaccount/**",
                "/updatepassword/**",
                "/passwordsave/**"
        };

        String[] userURLs = new String[]{
                "/search",
                "/profileaccount",
                "/profileaccountsave",
                "/profilepassword",
                "/profilepasswordsave",
                "/profilepicture",
                "/profilepicturesave",
                "/profileother",
                "/profileothersave",
                "/deleteaccount",
                "/follow/**",
                "/unfollow/**",
                "/following/**",
                "/**/following",
                "/**/followers",
                "/**",
                "/tweet",
                "/retweet/**",
                "/deleteTweet/**",
                "/deleteRetweet/**",
                "/home"
        };


        http.authorizeRequests()
                .antMatchers(resources)
                .permitAll()


                .antMatchers(adminURLs)
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()

                .antMatchers(userURLs)
                .hasRole("USER")
                .anyRequest()
                .authenticated()




                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler())
                .permitAll()


                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .permitAll();
    }


}