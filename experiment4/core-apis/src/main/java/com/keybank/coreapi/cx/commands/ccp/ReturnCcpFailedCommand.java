package com.keybank.coreapi.cx.commands.ccp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Slf4j
public class ReturnCcpFailedCommand {

    @TargetAggregateIdentifier
    private String ccpId;
    private String status;
    private String name;

    public ReturnCcpFailedCommand ( String ccpId, String status, String name ) {
        this.ccpId = ccpId;
        this.status = status;
        this.name = name;
    }
}
