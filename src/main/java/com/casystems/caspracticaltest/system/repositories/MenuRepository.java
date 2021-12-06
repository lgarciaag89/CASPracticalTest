package com.casystems.caspracticaltest.system.repositories;

import com.casystems.caspracticaltest.system.models.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends CrudRepository<Menu,Long> {
    public Optional<Menu> findByName(String name);
}
