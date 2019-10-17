package com.keybank.coreapi.cx.commands.ent;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.modelling.saga.SagaLifecycle;

@Data
@Slf4j
public class CreateUserEntCommand {

    @TargetAggregateIdentifier
    private String entId;
    private String name;
    private String status;


    public CreateUserEntCommand ( String entId,String name ) {
        this.entId = entId;
        this.name = name;
    }
}
