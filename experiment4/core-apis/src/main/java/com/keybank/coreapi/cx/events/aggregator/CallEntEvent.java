package com.keybank.coreapi.cx.events.aggregator;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class CallEntEvent {

    private String aggregatorId;
    private String name;
    private String status;

    public CallEntEvent ( ) {
    }

    public CallEntEvent ( String aggregatorId, String name ) {
        this.aggregatorId = aggregatorId;
        this.name = name;
    }


}
