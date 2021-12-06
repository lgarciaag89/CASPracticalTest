package com.casystems.caspracticaltest.system.services;


import com.casystems.caspracticaltest.system.models.User;
import com.casystems.caspracticaltest.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    public ArrayList<User> getUsers() {
        return (ArrayList<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveOrUpdateUser(User user) {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder(10);
        user.setPassword(bc.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean delUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
