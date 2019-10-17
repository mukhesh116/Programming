package com.keybank.coreapi.cx.commands.aggregator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Slf4j
public class ReturnCommand {

    @TargetAggregateIdentifier
    private String aggregatorId;
    private String status;
    private String name;

    public ReturnCommand ( ) {
    }


    public ReturnCommand ( String aggregatorId, String status, String name ) {
        this.aggregatorId = aggregatorId;
        this.status = status;
        this.name = name;
    }
}
