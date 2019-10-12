package com.keybank.entdemoservice.aggregates;


import com.keybank.coreapi.cx.commands.EntCreateCompanyUserCommand;
import com.keybank.coreapi.cx.commands.HandingOverToAggregatorCommand;
import com.keybank.coreapi.cx.events.CallingCcpCompanyEvent;
import com.keybank.coreapi.cx.events.EntCreateCompanyUserEvent;
import com.keybank.coreapi.cx.events.HandingOverToAggregatorEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
@Data
public class CreateUserAggregate {


    private String entID;

    @AggregateIdentifier
    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String purpose;

    private String status;

    public CreateUserAggregate() {
    }



    //TODO: Explore if we can generate the EntCreateCompanyUserEvent in a handle method for the EntCreateCompanyUserCommand and not in the constructor
    // as the handling of the command involves invoking database, etc. and should not be done in constructor
    @CommandHandler
    public CreateUserAggregate(EntCreateCompanyUserCommand entCreateCompanyUserCommand){

        log.info("ENT will invoke Service component");
        log.info("Handling the Logic");
//        if(Math.random() >= 0.85) {
//            log.info ("Radaom Eorror");
//            throw ( new RuntimeException ( "Random failure during Ent" ) );
//        }
        AggregateLifecycle.apply(new EntCreateCompanyUserEvent(entCreateCompanyUserCommand.getEntId(),entCreateCompanyUserCommand.getUserId(),
                                        entCreateCompanyUserCommand.getFirstName(), entCreateCompanyUserCommand.getLastName(), entCreateCompanyUserCommand.getEmail()
                                        , entCreateCompanyUserCommand.getPhone(), entCreateCompanyUserCommand.getPurpose(), entCreateCompanyUserCommand.getStatus()));
    }

    @EventSourcingHandler
    protected void on(EntCreateCompanyUserEvent entCreateCompanyUserEvent){
        this.entID=entCreateCompanyUserEvent.getEntId();
        this.userId = entCreateCompanyUserEvent.getUserId();
        this.firstName = entCreateCompanyUserEvent.getFirstName();
        this.lastName = entCreateCompanyUserEvent.getLastName();
        this.email = entCreateCompanyUserEvent.getEmail();
        this.phone = entCreateCompanyUserEvent.getPhone();
        this.purpose = entCreateCompanyUserEvent.getPurpose();
        this.status = entCreateCompanyUserEvent.getStatus();
        log.info("Company User Crated by ENT");



    }


    @CommandHandler
    public void on( HandingOverToAggregatorCommand cmd) {
        AggregateLifecycle.apply(new HandingOverToAggregatorEvent (cmd.getFirstName(),cmd.getLastName(),
                cmd.getEmail(),cmd.getPhone(),cmd.getPurpose(),cmd.getStatus(),cmd.getUserId ()));

    }

    @EventSourcingHandler
    public void on( HandingOverToAggregatorEvent event){
        this.userId = event.getUserId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.purpose = event.getPurpose();
        this.status = event.getStatus();
        log.info ("Ent updated sucessfully now handing over to aggregator for calling ccp");
        log.info ("Publishing Event to Aggregator ");
    }



}

