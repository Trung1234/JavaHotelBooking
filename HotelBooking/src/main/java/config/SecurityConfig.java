package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF for simplicity (not recommended for production)
            .authorizeRequests()
                .requestMatchers("/login").permitAll()  // Allow access to login API
                .anyRequest().authenticated()  // Secure all other endpoints
            .and()
            .formLogin()
                .loginProcessingUrl("/login")  // Specify the login URL
                .defaultSuccessUrl("/home", true)  // Redirect to home after successful login
            .and()
            .logout()
                .logoutUrl("/logout")  // Specify the logout URL
                .logoutSuccessUrl("/login");  // Redirect to login after logout
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for password hashing
    }
}