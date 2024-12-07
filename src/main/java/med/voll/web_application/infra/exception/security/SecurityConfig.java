package med.voll.web_application.infra.exception.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService user1() {
        UserDetails user1 = User.builder().username("gui@email.com").password("{noop}123").build();
        UserDetails user2 = User.builder().username("isa@email.com").password("{noop}321").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(req -> {
            req.requestMatchers("/css/**", "/js/**", "/assets/**").permitAll();
            req.anyRequest().authenticated();})
                .formLogin(form ->
                        form.loginPage("/login").permitAll().defaultSuccessUrl("/")).build();
    }
}
