package com.keybank.coreapi.cx.commands.ccp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Slf4j
@Data
public class DeleteUserCcpCommand {

    @TargetAggregateIdentifier
    private String ccpId;
    private String status;
    private String name;

    public DeleteUserCcpCommand ( String ccpId, String name, String status ) {
        this.ccpId = ccpId;
        this.status =status;
        this.name = name;
    }
}
