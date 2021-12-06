package com.casystems.caspracticaltest.system.controllers;

import com.casystems.caspracticaltest.system.models.Role;
import com.casystems.caspracticaltest.system.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping(path = "")
    public String getMessage() {
        return messageService.getMessage();
    }
}
