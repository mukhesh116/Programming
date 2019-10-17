package com.keybank.entdemoservice.aggregates;


import com.keybank.coreapi.cx.commands.ent.CreateUserEntCommand;
import com.keybank.coreapi.cx.commands.ent.DeleteUserEntCommand;
import com.keybank.coreapi.cx.events.ent.CreateUserEntEvent;
import com.keybank.coreapi.cx.events.ent.DeleteUserEntEvent;
import com.keybank.entdemoservice.service.Service;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aggregate
@Slf4j
@Data
@Component
public class EntCreateUserAggregate {

    @AggregateIdentifier
    private String entID;
    private String name;
    private String status;

    @Autowired
    Service service;

    @CommandHandler
    public EntCreateUserAggregate ( CreateUserEntCommand createUserEntCommand ) {
        log.info ( "*** Reached to Ent Service" );
        if ( Math.random ( ) >= 0.8 ) {
            log.info ( "    Ent Throws Error and service Failed " );

            createUserEntCommand.setStatus ( "Ent Service Failed Plz try Again later" );

            throw new RuntimeException ( "Random failure during Ent" );
        }
        /*The Invocation of service is not working in the Aggreagator Classs Need help with that*/
        //EntServiceResponse response = service.create ( createUserEntCommand.getName () );

        createUserEntCommand.setStatus ( "User Created Successfully in Ent Service" );
        AggregateLifecycle.apply ( new CreateUserEntEvent(createUserEntCommand.getEntId (),createUserEntCommand.getName (),createUserEntCommand.getStatus ()) );

    }

    @EventSourcingHandler
    protected void on ( CreateUserEntEvent createUserEntEvent ) {
        this.entID = createUserEntEvent.getEntId ( );
        this.name =createUserEntEvent.getName ();
        this.status = createUserEntEvent.getStatus ( );
        log.info ( "**** Company User Crated by ENT" );

    }


    @CommandHandler
    public void handle ( DeleteUserEntCommand deleteUserEntCommand ) {
        log.info ( "*** Reached to Ent Service" );
        if ( Math.random ( ) >= 0.8 ) {
            log.info ( "    Ent Throws Error and service Failed " );

            deleteUserEntCommand.setStatus ( "Ent Service Failed Plz try Again later" );

            throw new RuntimeException ( "Random failure during Ent" );
        }

        deleteUserEntCommand.setStatus ( "User Created Successfully in Ent Service" );

        /*The Invocation of service is not working in the Aggreagator Classs Need help with that*/
//        EntServiceResponse response = service.delete (deleteUserEntCommand.getName ());

        AggregateLifecycle.apply ( new DeleteUserEntEvent (deleteUserEntCommand.getEntId (),deleteUserEntCommand.getName (),deleteUserEntCommand.getStatus ()) );

    }
    @EventSourcingHandler
    protected void on ( DeleteUserEntEvent deleteUserEntEvent ) {
        this.entID = deleteUserEntEvent.getEntId ( );
        this.name =deleteUserEntEvent.getName ();
        this.status = deleteUserEntEvent.getStatus ( );
        log.info ( "**** Company User Deleted by ENT" );

    }

}

