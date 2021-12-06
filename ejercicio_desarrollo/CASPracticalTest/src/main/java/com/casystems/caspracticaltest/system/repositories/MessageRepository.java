package com.casystems.caspracticaltest.system.repositories;

import com.casystems.caspracticaltest.system.models.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message,Long> {

}
