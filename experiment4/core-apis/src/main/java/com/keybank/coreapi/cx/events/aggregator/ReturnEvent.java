package com.keybank.coreapi.cx.events.aggregator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ReturnEvent {

    private String aggregatorId;
    private String status;
    private String name;

    public ReturnEvent ( ) {
    }

    public ReturnEvent ( String aggregatorId, String status, String name ) {
        this.aggregatorId = aggregatorId;
        this.status = status;
        this.name = name;
    }
}
