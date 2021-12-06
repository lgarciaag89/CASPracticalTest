package com.casystems.caspracticaltest.system.controllers;

import com.casystems.caspracticaltest.system.models.User;
import com.casystems.caspracticaltest.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserService userService;

    @GetMapping()
    public ArrayList<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    public User saveUser(@RequestBody User user) {
        return userService.saveOrUpdateUser(user);
    }

    @PutMapping()
    public User updateUser(@RequestBody User user) {
        return userService.saveOrUpdateUser(user);
    }

    @DeleteMapping(path = {"/{id}"})
    public String delUserById(@PathVariable("id") Long id ){
        if(userService.delUser(id)){
            return String.format("User %d deleted",id);
        }else{
            return String.format("User %d can be deleted",id);
        }
    }
}
