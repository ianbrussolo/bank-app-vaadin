////package com.bankapp.application.security;
////
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
////import org.springframework.security.web.SecurityFilterChain;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests((requests) -> requests
////                        .antMatchers("/home").authenticated()
////                        .anyRequest().permitAll()
////                )
////                .formLogin((form) -> form
////                        .loginPage("/login")
////                        .permitAll()
////                )
////                .logout((logout) -> logout.permitAll());
////
////        return http.build();
////    }
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                    .antMatchers("/home").authenticated()
////                    .anyRequest().permitAll()
////                    .and()
////                .formLogin()
////                    .loginPage("/login")
////                    .defaultSuccessUrl("/home", true)
////                    .permitAll()
////                    .and()
////                .logout()
////                    .permitAll()
////                    .and()
////                .csrf().disable();
////    }
//}
