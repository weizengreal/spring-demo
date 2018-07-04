package com.woai662.demo.service;

import com.woai662.demo.entity.User;
import com.woai662.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByName(String name) {
        User user = null;
        try {
            user = userRepository.findByUserName(name);
        } catch (Exception e) {

        }
        return user;
    }

}
