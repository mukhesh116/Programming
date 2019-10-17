package com.keybank.coreapi.cx.events.aggregator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class CallCcpEvent {

    private String aggregatorId;
    private String name;
    private String status;

    public CallCcpEvent ( ) {

    }

    public CallCcpEvent ( String aggregatorId, String name, String status ) {
        this.aggregatorId = aggregatorId;
        this.name = name;
        this.status = status;
    }
}
