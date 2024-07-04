package com.example.MinorProject.DigitalLibrary.Config;


import com.example.MinorProject.DigitalLibrary.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Autowired
    UserService userService;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService);
        return authenticationManagerBuilder.build();

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.

                 authorizeHttpRequests((auth) -> auth
                         .requestMatchers( "/swagger-ui/**", "/v3/api-docs/**","/swagger-resources/**", "/swagger-resources","/webjars/**").permitAll()
                        .requestMatchers("/student/details/**").hasAuthority("get-profile")
                        .requestMatchers(HttpMethod.GET,"/student/**").hasAuthority("get-student-details")
                        .requestMatchers(HttpMethod.POST,"/student/**").permitAll()
                        .requestMatchers("/student/**").hasAuthority("update-profile")
                        .requestMatchers(HttpMethod.POST,"/createAdmin").hasAuthority("create-admin")
                        .requestMatchers(HttpMethod.GET,"/book/**").hasAuthority("get-book-details")
                        .requestMatchers("/book/**").hasAuthority("update-book")
                        .requestMatchers("/transaction/**").hasAuthority("book-transactionDetails"))
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults());


                return http.build();
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
