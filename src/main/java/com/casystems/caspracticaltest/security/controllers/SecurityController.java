package com.casystems.caspracticaltest.security.controllers;

import com.casystems.caspracticaltest.security.validators.UserValidator;
import com.casystems.caspracticaltest.system.models.Role;
import com.casystems.caspracticaltest.system.models.User;
import com.casystems.caspracticaltest.system.services.RoleService;
import com.casystems.caspracticaltest.system.services.UserService;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;


@Controller
public class SecurityController {

    private static final String SIGUP = "sigup";

    private final RoleService roleService;
    private final UserService userService;
    private final  UserValidator userValidator;

    @Inject
    public SecurityController(RoleService roleService, UserService userService, UserValidator userValidator) {
        Assert.notNull(roleService,"RoleService most not be null");
        Assert.notNull(userService,"UserService most not be null");
        Assert.notNull(userValidator,"UserValidator most not be null");
        this.roleService = roleService;
        this.userService = userService;
        this.userValidator = userValidator;
    }



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
        return SIGUP;
    }

    @PostMapping("/sigup")
    public String registration(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);

        if(!bindingResult.hasErrors()) {
            userService.saveOrUpdateUser(user);
            ArrayList<Role> roles = roleService.getNotAdminRoles();
            User newUser = new User();
            newUser.setRoles(new HashSet<>(roles));
            model.addAttribute("user", newUser);
            model.addAttribute("message", "Successful!");
        }
        return SIGUP;
    }


}
