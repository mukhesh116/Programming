package com.keybank.coreapi.cx.events.ccp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
public class ReturnCcpFailedEvent {

    private String ccpId;
    private String status;
    private String name;

    public ReturnCcpFailedEvent ( String ccpId, String status, String name ) {
        this.ccpId = ccpId;
        this.status = status;
        this.name = name;
    }
}
