package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;


@Configuration
public class WebSecurityConfig {
    
    // // permit all requests of web application
    // @Bean
    // SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    //     http.authorizeHttpRequests().anyRequest().permitAll()
    //             .and().formLogin(withDefaults()).httpBasic(withDefaults());
    

    // // deny all requests of web application
    // @Bean
    // SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    //     http.authorizeHttpRequests().anyRequest().denyAll()
    //             .and().formLogin(withDefaults()).httpBasic(withDefaults());
    // return http.build();

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().ignoringRequestMatchers("/saveMsg").ignoringRequestMatchers(PathRequest.toH2Console()).and()
                .authorizeHttpRequests()
                .requestMatchers("/dashboard").authenticated()
                .requestMatchers("", "/", "/home").permitAll()
                .requestMatchers("/holidays/**").permitAll()
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/saveMsg").permitAll()
                .requestMatchers("/courses").permitAll()
                .requestMatchers("/about").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/logout").permitAll()                
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .requestMatchers("/assets/**").permitAll()
                .and().formLogin(login -> login.loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll())
                .httpBasic(withDefaults());
            
        http.headers().frameOptions().disable();

    return http.build();
    

    // // without using thymeleaf for submit contact form.
    // @Bean
    // SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    //     http.csrf().disable()
    //     .authorizeHttpRequests()
    //             .requestMatchers("", "/", "/home").permitAll()
    //             .requestMatchers("/holidays/**").permitAll()
    //             .requestMatchers("/contact").permitAll()
    //             .requestMatchers("/saveMsg").authenticated()
    //             .requestMatchers("/courses").permitAll()
    //             .requestMatchers("/about").permitAll()
    //             .requestMatchers("/assets/**").permitAll()
    //             .and().formLogin().httpBasic();
    // return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
