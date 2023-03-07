//package com.bankapp.application.data.service;
//
//import com.bankapp.application.data.entity.User;
//import com.bankapp.application.data.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.io.Serializable;
//import java.util.Collections;
//
//@Service
//public class AuthenticationService implements Serializable {
//    @Autowired
//    private final UserDetailsService userDetailsService;
//
//    public AuthenticationService(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    public UserDetails authenticate(String username, String password) {
//        try {
//            UserDetails user = userDetailsService.loadUserByUsername(username);
//            if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {
//                return user;
//            }
//        } catch (UsernameNotFoundException ex) {
//            //usuario nao encontrado
//        }
//        return null;
//    }
//
//}
