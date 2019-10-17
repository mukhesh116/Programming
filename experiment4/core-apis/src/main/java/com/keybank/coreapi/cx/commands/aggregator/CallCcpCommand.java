package com.keybank.coreapi.cx.commands.aggregator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Slf4j
public class CallCcpCommand {

    @TargetAggregateIdentifier
    private String aggregatorId;
    private String name;
    private String status;

    public CallCcpCommand() {
    }

    public CallCcpCommand(String aggregatorId,  String name, String status) {
        this.aggregatorId = aggregatorId;
        this.name =name;
        this.status = status;
    }
}
