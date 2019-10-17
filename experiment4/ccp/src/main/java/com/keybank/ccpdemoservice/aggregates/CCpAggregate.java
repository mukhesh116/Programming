package com.keybank.ccpdemoservice.aggregates;


import com.keybank.ccpdemoservice.response.CcpServiceResponse;
import com.keybank.ccpdemoservice.service.Service;
import com.keybank.coreapi.cx.commands.ccp.CreateUserCcpCommand;
import com.keybank.coreapi.cx.commands.ent.CreateUserEntCommand;
import com.keybank.coreapi.cx.events.ccp.CreateUserCcpEvent;
import com.keybank.coreapi.cx.events.ent.CreateUserEntEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

@Aggregate
@Slf4j
@Data
public class CCpAggregate {

    @AggregateIdentifier
    private String entID;
    private String name;
    private String status;


    @Autowired
    Service service;


    @CommandHandler
    public CCpAggregate ( CreateUserCcpCommand createUserCcpCommand ) {
        log.info ( "*** Reached to CCP Service" );

        if ( Math.random ( ) >= 0.8 ) {
            log.info ( "    Ent Throws Error and service Failed " );

            createUserCcpCommand.setStatus ( "CCP Service Failed Plz try Again later" );

            throw new RuntimeException ( "Random failure during CCP" );
        }

        createUserCcpCommand.setStatus ( "User Created Successfully in CCP Service" );

        //CcpServiceResponse response = service.create ( createUserCcpCommand.getName () );

        AggregateLifecycle.apply ( new CreateUserCcpEvent (createUserCcpCommand.getCcpId (),createUserCcpCommand.getStatus (),createUserCcpCommand.getName ()) );

    }

    @EventSourcingHandler
    protected void on ( CreateUserCcpEvent createUserCcpEvent ) {
        this.entID = createUserCcpEvent.getCcpId ( );
        this.name =createUserCcpEvent.getName ();
        this.status = createUserCcpEvent.getStatus ( );
        log.info ( "**** Company User Crated by CCP" );

    }
}

