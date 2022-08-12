package bg.softuni.movies.config;

import bg.softuni.movies.models.enums.UserRoleEnum;
import bg.softuni.movies.repository.UserRepository;
import bg.softuni.movies.services.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.
                        authorizeRequests().
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                // everyone can login and register
                        antMatchers("/", "/login", "/register",
                        "/home", "/movies/add-movie", "/movies/movie-details",
                        "/actors/add-actor", "/actor/actor-details").permitAll().
                        antMatchers("/js/**", "/css/**", "/images/**", "/videos/**").permitAll().
                        antMatchers("/user").hasRole(UserRoleEnum.USER.name()).
//                // pages available only for admins
                        antMatchers("/movies/add-movie").hasRole(UserRoleEnum.ADMIN.name()).
                        anyRequest().
                authenticated().
                and().
                        formLogin().
                        loginPage("/login").
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).

//                        defaultSuccessUrl("/").
                        defaultSuccessUrl("/home", true).
                        failureForwardUrl("/login-error").
                and().
                        logout().clearAuthentication(true).
                        logoutUrl("/logout").
                // on logout go to the home page
//                        logoutSuccessUrl("/login").
                        invalidateHttpSession(true).
                deleteCookies("JSESSIONID");
//        .and().csrf().disable(); //if you wanna to disable csrf


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }


}