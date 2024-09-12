package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF for simplicity (not recommended for production)
            .authorizeRequests()
                .requestMatchers("/signin").permitAll()  // Allow access to login API
                .anyRequest().authenticated()  // Secure all other endpoints
            .and()
            .formLogin()
                .loginProcessingUrl("/signin")  // Specify the login URL
                .defaultSuccessUrl("/home", true)  // Redirect to home after successful login
            .and()
            .logout()
                .logoutUrl("/logout")  // Specify the logout URL
                .logoutSuccessUrl("/signin")
             .and().authorizeRequests()
             .requestMatchers("/signup").permitAll() // Permit public endpoints
             .anyRequest().authenticated()          // Other requests require authentication
             .and()
         .httpBasic();  // Redirect to login after logout
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for password hashing
    }
    
    @Bean
    public AuthenticationManager authenticationManager(
                                 AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        //authorize.anyRequest().authenticated()
                        authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated()

                );

        return http.build();
    }
}