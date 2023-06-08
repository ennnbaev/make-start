package ua.nure.makestart.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class AuthConfigurations {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/v2/api-docs").permitAll()
                .requestMatchers("/configuration/ui").permitAll()
                .requestMatchers("/swagger-resources/**").permitAll()
                .requestMatchers("/configuration/security").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/swagger-ui/*").permitAll()
                .requestMatchers("/webjars/**").permitAll()
                .requestMatchers("/v2/**").permitAll()
                .requestMatchers("/**").permitAll()
                .requestMatchers("/**").permitAll()
                .requestMatchers("/user/cv/**").permitAll()
                .requestMatchers("login/**").authenticated()
                .anyRequest().authenticated()
                .and().build();

    }


}
