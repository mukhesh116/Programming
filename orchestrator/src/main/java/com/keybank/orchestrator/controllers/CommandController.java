package com.keybank.orchestrator.controllers;

import com.keybank.orchestrator.dto.User;
import com.keybank.orchestrator.services.CommandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/create")
public class CommandController {

    private CommandService commandService;

    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public CompletableFuture<String> createOrder(@RequestBody User user){
        return commandService.createUser(user);
    }
}
