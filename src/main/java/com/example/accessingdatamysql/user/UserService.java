package com.example.accessingdatamysql.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());

        if(userByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }

        userRepository.save(user);
    }
}
