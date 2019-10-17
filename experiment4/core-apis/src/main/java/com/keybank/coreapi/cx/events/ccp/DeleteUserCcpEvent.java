package com.keybank.coreapi.cx.events.ccp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Data
public class DeleteUserCcpEvent {


    private String ccpId;
    private String status;
    private String name;

    public DeleteUserCcpEvent ( String ccpId, String status, String name ) {
        this.ccpId = ccpId;
        this.status = status;
        this.name = name;
    }
}