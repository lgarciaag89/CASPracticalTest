package com.casystems.caspracticaltest.system.repositories;

import com.casystems.caspracticaltest.system.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}
