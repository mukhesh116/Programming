package com.keybank.orchestrator.services;



import com.keybank.coreapi.cx.commands.aggregator.CallEntCommand;
import com.keybank.coreapi.cx.events.aggregator.ReturnEvent;
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
    public CompletableFuture< ReturnEvent > createUser( User user) {

        String aggregatorId = UUID.randomUUID().toString();

        CallEntCommand callEntCommand = new CallEntCommand (aggregatorId,user.getName ());

        return commandGateway.send(callEntCommand);

    }
}
