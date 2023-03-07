package com.bankapp.application.data.service;

import com.bankapp.application.data.entity.User;
import com.bankapp.application.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    //criar usuario
    public User saveUser (String name, String email, String password) {
        Long code = ThreadLocalRandom.current().nextLong(10000, 10000);
        User user = new User(name, email, password);
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
