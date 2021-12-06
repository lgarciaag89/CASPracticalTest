package com.casystems.caspracticaltest.security.controllers;

import com.casystems.caspracticaltest.security.validators.UserValidator;
import com.casystems.caspracticaltest.system.models.Role;
import com.casystems.caspracticaltest.system.models.User;
import com.casystems.caspracticaltest.system.services.RoleService;
import com.casystems.caspracticaltest.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;


@Controller
public class SecurityController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;


    @GetMapping({"/","/login"})
    public String index(){
        return "index";
    }

    @GetMapping("/sigup")
    public String registration(Model model) {
        ArrayList<Role> roles = roleService.getNotAdminRoles();
        User user  = new User();
        user.setRoles(new HashSet<>(roles));
        model.addAttribute("user", user);
        return "sigup";
    }

    @PostMapping("/sigup")
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "sigup";
        }

        userService.saveOrUpdateUser(user);
        ArrayList<Role> roles = roleService.getNotAdminRoles();
        User newUser  = new User();
        newUser.setRoles(new HashSet<>(roles));
        model.addAttribute("user", newUser);
        model.addAttribute("message", "Successful!");
        return "sigup";
    }


}
