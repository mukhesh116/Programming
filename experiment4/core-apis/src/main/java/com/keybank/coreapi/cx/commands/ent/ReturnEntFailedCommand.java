package com.keybank.coreapi.cx.commands.ent;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Slf4j
public class ReturnEntFailedCommand {

    @TargetAggregateIdentifier
    private String entId;
    private String status;
    private String name;

    public ReturnEntFailedCommand ( String entId, String status, String name ) {
        this.entId = entId;
        this.status = status;
        this.name = name;
    }
}
