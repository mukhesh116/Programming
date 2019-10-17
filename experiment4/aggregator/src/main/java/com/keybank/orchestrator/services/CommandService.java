package com.keybank.orchestrator.services;



import com.keybank.coreapi.cx.events.aggregator.ReturnEvent;
import com.keybank.orchestrator.dto.User;

import java.util.concurrent.CompletableFuture;

public interface CommandService {

    public CompletableFuture< ReturnEvent > createUser( User user);

}
