package com.casystems.caspracticaltest.system.services;

import com.casystems.caspracticaltest.system.models.Message;
import com.casystems.caspracticaltest.system.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Value("${message_id}")
    private Long menssage_id;
    @Autowired
    MessageRepository messageRepository;

    public String getMessage(){
        Optional<Message> message = messageRepository.findById(menssage_id);
        if(message.isPresent())
            return message.get().getMessage();
        return "There is not message";
    }

}
