//package com.bankapp.application.security;
//
//import com.bankapp.application.data.entity.User;
//import com.bankapp.application.data.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.Collections;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    public UserRepository userRepository;
//
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("Usuário não encontrado");
//        }
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                Collections.emptyList()
//        );
//    }
//}
