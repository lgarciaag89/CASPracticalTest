package com.casystems.caspracticaltest.system.controllers;

import com.casystems.caspracticaltest.system.models.Role;
import com.casystems.caspracticaltest.system.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping()
    public ArrayList<Role> getUsers() {
        return roleService.getRoles();
    }

    @GetMapping(path = "/{id}")
    public Optional<Role> getRole(@PathVariable("id") Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping()
    public Role saveRole(@RequestBody Role role) {
        return roleService.saveOrUpdateRole(role);
    }

    @PutMapping()
    public Role updateRole(@RequestBody Role role) {
        return roleService.saveOrUpdateRole(role);
    }

    @DeleteMapping(path = {"/{id}"})
    public String delRoleById(@PathVariable("id") Long id ) {
        if(roleService.delRole(id)){
            return String.format("Role %d deleted",id);
        }else{
            return String.format("Role %d can be deleted",id);
        }
    }
}
