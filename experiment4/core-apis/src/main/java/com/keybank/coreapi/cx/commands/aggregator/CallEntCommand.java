package com.keybank.coreapi.cx.commands.aggregator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Data
@Slf4j
public class CallEntCommand {



    @TargetAggregateIdentifier
    private String aggregatorId;
    private String status;
    private String name;

    public CallEntCommand() {
    }

    public CallEntCommand(String aggregatorId, String name) {
        this.aggregatorId = aggregatorId;
        this.name = name;
    }
}
