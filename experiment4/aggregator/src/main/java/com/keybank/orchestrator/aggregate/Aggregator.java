package com.keybank.orchestrator.aggregate;


import com.keybank.coreapi.cx.commands.aggregator.CallCcpCommand;
import com.keybank.coreapi.cx.commands.aggregator.CallEntCommand;
import com.keybank.coreapi.cx.commands.aggregator.ReturnCommand;
import com.keybank.coreapi.cx.events.aggregator.CallCcpEvent;
import com.keybank.coreapi.cx.events.aggregator.CallEntEvent;
import com.keybank.coreapi.cx.events.aggregator.ReturnEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
@Data
public class Aggregator {

    @AggregateIdentifier
    private String aggregatorId;
    private String name;
    private String status;

    public Aggregator ( ) {
    }

    @CommandHandler
    public Aggregator ( CallEntCommand callEntCommand ) {
        AggregateLifecycle.apply ( new CallEntEvent ( callEntCommand.getAggregatorId ( ), callEntCommand.getName ( ) ) );
    }


    @EventSourcingHandler
    protected void on ( CallEntEvent callEntEvent ) {
        this.aggregatorId = callEntEvent.getAggregatorId ( );
        this.name = callEntEvent.getName ( );
        log.info ( "** Hand over to Saga Manager from service, will call the CCP" );

    }

    @CommandHandler
    public void Aggregator ( CallCcpCommand callCcpCommand ) {
        AggregateLifecycle.apply ( new CallCcpEvent ( callCcpCommand.getAggregatorId ( ), callCcpCommand.getName ( ), callCcpCommand.getStatus ( ) ) );
    }


    @EventSourcingHandler
    protected void on ( CallCcpEvent callCcpEvent ) {
        this.aggregatorId = callCcpEvent.getAggregatorId ( );
        this.name = callCcpEvent.getName ( );
        this.status = callCcpEvent.getStatus ( );
        log.info ( "*** User Updated in Ent Sucessfully now we hand over to Saga Manager will call the CCP" );

    }


    @CommandHandler
    protected void Aggregator ( ReturnCommand returnCommand ) {
        AggregateLifecycle.apply ( new ReturnEvent ( returnCommand.getAggregatorId ( ), returnCommand.getName ( ),
                returnCommand.getStatus ( ) ) );
    }

    @EventSourcingHandler
    protected void on ( ReturnEvent returnEvent ) {
        this.aggregatorId = returnEvent.getAggregatorId ( );
        this.name = returnEvent.getName ( );
        this.status = returnEvent.getStatus ( );
        log.info ( "****Returning Updated Status back to UI" );
    }


}

