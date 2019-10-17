package com.keybank.coreapi.cx.events.ent;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Slf4j
public class CreateUserEntEvent {

    private String entId;
    private String status;
    private String name;


    public CreateUserEntEvent ( String entId, String status, String name ) {
        this.entId = entId;
        this.status = status;
        this.name = name;
    }
}
