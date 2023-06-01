package ua.nure.makestart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import ua.nure.makestart.service.UserService;

@EnableWebSecurity
public class AuthConfigurations {

    @Autowired
    private UserService userSevice;

    @Bean
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


}
