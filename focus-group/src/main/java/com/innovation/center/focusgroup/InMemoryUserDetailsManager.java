package com.innovation.center.focusgroup;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import com.innovation.center.focusgroup.SpringSecurityConfig;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class InMemoryUserDetailsManager {
    public InMemoryUserDetailsManager(UserDetails ramesh, UserDetails admin) {
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails ramesh = User.builder()
                .username("ramesh")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(ramesh, admin).userDetailsService();
    }


}