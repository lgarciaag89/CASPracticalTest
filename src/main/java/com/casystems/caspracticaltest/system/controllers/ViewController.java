package com.casystems.caspracticaltest.system.controllers;

import com.casystems.caspracticaltest.system.models.Menu;
import com.casystems.caspracticaltest.system.models.Role;
import com.casystems.caspracticaltest.system.models.User;
import com.casystems.caspracticaltest.system.services.RoleService;
import com.casystems.caspracticaltest.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ViewController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/showinfouser")
    public String showinfouser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        User user = userService.getUserByUsername(userDetail.getUsername()).get();
        model.addAttribute("user",user);

        Set<Menu> menus = new LinkedHashSet<>();
        for(GrantedAuthority authority:auth.getAuthorities()){
            Optional<Role> role = roleService.getRoleByRole(authority.getAuthority());
            if(role.isPresent()){
                menus.addAll(role.get().getMenus());
            }
        }
        List<Menu> listaMenuSorted = new LinkedList<Menu>(menus.stream().sorted(new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }).collect(Collectors.toList()));
        model.addAttribute("menus",listaMenuSorted);
        return "showinfouser";
    }

    @GetMapping("/showrole")
    public String showroles(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Set<Menu> menus = new LinkedHashSet<>();
        for(GrantedAuthority authority:auth.getAuthorities()){
            Optional<Role> role = roleService.getRoleByRole(authority.getAuthority());
            if(role.isPresent()){
                menus.addAll(role.get().getMenus());
            }
        }
        List<Menu> listaMenuSorted = new LinkedList<Menu>(menus.stream().sorted(new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }).collect(Collectors.toList()));
        ArrayList<Role> roles = roleService.getRoles();
        model.addAttribute("roles",roles);
        model.addAttribute("menus",listaMenuSorted);
        return "showrole";
    }

    @GetMapping("/showuser")
    public String showusers(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Set<Menu> menus = new LinkedHashSet<>();
        for(GrantedAuthority authority:auth.getAuthorities()){
            Optional<Role> role = roleService.getRoleByRole(authority.getAuthority());
            if(role.isPresent()){
                menus.addAll(role.get().getMenus());
            }
        }
        List<Menu> listaMenuSorted = new LinkedList<Menu>(menus.stream().sorted(new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }).collect(Collectors.toList()));
        ArrayList<User> users = userService.getUsers();
        model.addAttribute("users",users);
        model.addAttribute("menus",listaMenuSorted);
        return "showuser";
    }

    @GetMapping("/menu")
    public String menu(@ModelAttribute("user") User user, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Set<Menu> menus = new LinkedHashSet<>();
        for(GrantedAuthority authority:auth.getAuthorities()){
            Optional<Role> role = roleService.getRoleByRole(authority.getAuthority());
            if(role.isPresent()){
                menus.addAll(role.get().getMenus());
            }
        }
        List<Menu> listaMenuSorted = new LinkedList<Menu>(menus.stream().sorted(new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }).collect(Collectors.toList()));
        model.addAttribute("menus",listaMenuSorted);
        return "menu";
    }
}
