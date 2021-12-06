package com.casystems.caspracticaltest.system.services;

import com.casystems.caspracticaltest.system.models.Menu;
import com.casystems.caspracticaltest.system.models.Role;
import com.casystems.caspracticaltest.system.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    RoleService roleService;

    public ArrayList<Menu> getMenus() {
        return (ArrayList<Menu>) menuRepository.findAll();
    }

    public Optional<Menu> getMenuById(Long id) {
        return menuRepository.findById(id);
    }

    public Menu saveOrUpdateMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public boolean delMenu(Long id)  {
        try{
            Menu menu = menuRepository.findById(id).orElseThrow(() -> new Exception());
            menuRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

    public Optional<Menu> getMenuByName(String nameMenu){
        return menuRepository.findByName(nameMenu);
    }

    public ArrayList<Role> getAuthorities4MenuName(String menuName) {

        Optional<Menu> menu = getMenuByName(menuName);
        if(!menu.isPresent()){
            return new ArrayList<>();
        }
        return roleService.getRoles4Menu(menuName);
    }
}
