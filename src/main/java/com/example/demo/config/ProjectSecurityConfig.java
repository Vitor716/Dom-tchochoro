package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/dashboard").authenticated()
                .requestMatchers("/employee").hasRole("ADMIN")
                .requestMatchers("/displayProfile").hasRole("ADMIN")
                .requestMatchers("/private/**").hasRole("ADMIN")
                .requestMatchers("/registerAdm").hasRole("ADMIN")
                .requestMatchers("/saveAdm").hasRole("ADMIN")
                .requestMatchers("/getAdmList").hasRole("ADMIN")
                .requestMatchers("/categories").hasRole("ADMIN")
                .requestMatchers("/saveCategory").hasRole("ADMIN")
                .requestMatchers("/updateAdminProfile").permitAll()
                .requestMatchers("/contact").permitAll()
                .requestMatchers("/saveMsg").permitAll()
                .requestMatchers("/displayMessages").permitAll()
                .requestMatchers("/closeMsg").permitAll()
                .requestMatchers("/profile").permitAll()
                .requestMatchers("/myProfile").permitAll()
                .requestMatchers("/editProfile").permitAll()
                .requestMatchers("/updateUserProfile").permitAll()
                .requestMatchers("", "/", "/home").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/cart").permitAll()
                .requestMatchers("/saveUser").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/logout").permitAll()
                .and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/",true).failureUrl("/login?error=false").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}