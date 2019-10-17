package com.keybank.coreapi.cx.events.ent;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Slf4j
@Data
public class DeleteUserEntEvent {

    private String entId;
    private String status;
    private String name;

    public DeleteUserEntEvent ( String entId, String status, String name ) {
        this.entId = entId;
        this.status = status;
        this.name = name;
    }
}
