package com.casystems.caspracticaltest.system.services;


import com.casystems.caspracticaltest.system.models.Menu;
import com.casystems.caspracticaltest.system.models.Role;
import com.casystems.caspracticaltest.system.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Value("#{'${indelible.roles}'.split(',')}")
    private List<String> rolesIndelibles;
    @Value("#{'${admin.role}'}")
    private String adminRole;

    @Autowired
    private RoleRepository roleRepository;

    public ArrayList<Role> getRoles() {
        return (ArrayList<Role>) roleRepository.findAll();
    }

    public ArrayList<Role> getNotAdminRoles() {
        ArrayList<Role> allRoles = (ArrayList<Role>) roleRepository.findAll();
        ArrayList<Role> rolesWithOutAdmin = new ArrayList<>();
        for(Role role: allRoles){
            if(!role.getRole().contains(adminRole))
                rolesWithOutAdmin.add(role);
        }
        return rolesWithOutAdmin;
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role saveOrUpdateRole(Role role) {
        return roleRepository.save(role);
    }

    public boolean delRole(Long id)  {
        try{
        Role role = roleRepository.findById(id).orElseThrow(() -> new Exception());
        if (rolesIndelibles.contains(role.getRole()))
            return false;
        roleRepository.deleteById(id);
        return true;
        }catch (Exception err){
            return false;
        }
    }

    public Optional<Role> getRoleByRole(String nameRole){

        return roleRepository.findByRole(nameRole);

    }

    public ArrayList<Role> getRoles4Menu(String menuName){
        ArrayList<Role> allRoles = (ArrayList<Role>) roleRepository.findAll();
        ArrayList<Role> roles4Menu = new ArrayList<>();
        for(Role role:allRoles) {
            for (Menu menu : role.getMenus()) {
                if(menu.getName().equals(menuName))
                    roles4Menu.add(role);
            }
        }
        return roles4Menu;
    }
}
