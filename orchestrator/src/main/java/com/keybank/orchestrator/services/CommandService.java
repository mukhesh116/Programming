package com.keybank.orchestrator.services;



import com.keybank.orchestrator.dto.User;

import java.util.concurrent.CompletableFuture;

public interface CommandService {

    public CompletableFuture<String> createUser(User user);

}
