package com.keybank.coreapi.cx.commands.ent;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Slf4j
@Data
public class DeleteUserEntCommand {

    @TargetAggregateIdentifier
    private String entId;
    private String status;
    private String name;

    public DeleteUserEntCommand ( String entId, String name, String status ) {
        this.entId = entId;
        this.name = name;
        this.name= status;
    }
}
