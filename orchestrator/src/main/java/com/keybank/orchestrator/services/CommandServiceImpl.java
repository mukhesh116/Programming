package com.keybank.orchestrator.services;


import com.keybank.coreapi.cx.commands.CreateUserCommand;
import com.keybank.orchestrator.dto.User;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CommandServiceImpl implements CommandService {

    private final CommandGateway commandGateway;

    public CommandServiceImpl(CommandGateway commandGateway) {

        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createUser(User user) {
        String userId = UUID.randomUUID().toString();
        CreateUserCommand createUserCommand = new CreateUserCommand(userId,user.getFirstName(),user.getLastName(),user.getEmail()
                ,user.getPhone(),user.getPurpose(),user.getStatus());
        return commandGateway.send(createUserCommand);

    }
}
