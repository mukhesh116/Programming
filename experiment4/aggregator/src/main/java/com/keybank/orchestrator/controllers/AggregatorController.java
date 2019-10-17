package com.keybank.orchestrator.controllers;

import com.keybank.coreapi.cx.events.aggregator.ReturnEvent;
import com.keybank.orchestrator.dto.User;
import com.keybank.orchestrator.services.CommandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/aggregator/create")
public class AggregatorController {

    private CommandService commandService;

    public AggregatorController ( CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public CompletableFuture<ReturnEvent> createOrder( @RequestBody User user){
        return commandService.createUser(user);
    }
}
