package com.example.EE.EE.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {
    //LDAP or Database
    //In Memory
    //InMemoryUserDetailsManager
    //InMemoryUserDetailsManager(UserDetails... users);



    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager(){

        UserDetails userDetails1 = createNewUser("johnjake14", "p@ssw0rd!");
        UserDetails userDetails2 = createNewUser("ninjin14", "p@ssw0rd!");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);

    }

    private UserDetails createNewUser(String username, String password) {
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
        UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder).username(username).password(password).roles("USER", "ADMIN").build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //All URLS are protected
    // A login form is shown for unathorized requests
    //CSRF disable
    //FRAMES

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()); //make sure all requests are authenticated
        http.formLogin(Customizer.withDefaults()); //collect username and password
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }

}
