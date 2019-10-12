package com.keybank.ccpdemoservice.aggregates;


import com.keybank.coreapi.cx.commands.CallingCcpCompanyCommand;
import com.keybank.coreapi.cx.commands.CcpCreateCompanyUserCommand;
import com.keybank.coreapi.cx.events.CCpCreateCompanyUserEvent;
import com.keybank.coreapi.cx.events.CCpCreateCompanyUserOnFaliureEvent;
import com.keybank.coreapi.cx.events.CallingCcpCompanyEvent;
import com.keybank.coreapi.cx.events.CallingEntCompanyEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;

import org.axonframework.spring.stereotype.Aggregate;



@Aggregate
@Slf4j
public class CompanyCreateUserAggregate {

    @AggregateIdentifier
    private  String ccpId;

    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String purpose;

    private String status;

    public CompanyCreateUserAggregate() {
    }

    @CommandHandler
    public CompanyCreateUserAggregate(CcpCreateCompanyUserCommand createCompanyUserCommand) {

        log.info("Calling the CCP service Component ");
        if(Math.random() >= 0.85){
            AggregateLifecycle.apply(new CCpCreateCompanyUserOnFaliureEvent(createCompanyUserCommand.getCcpId(), createCompanyUserCommand.getUserId(), createCompanyUserCommand.getStatus()));

            throw new RuntimeException("Random failure during CCP");

        }

            AggregateLifecycle.apply(new CCpCreateCompanyUserEvent(createCompanyUserCommand.getCcpId(), createCompanyUserCommand.getUserId(),
                    createCompanyUserCommand.getFirstName(), createCompanyUserCommand.getLastName(), createCompanyUserCommand.getEmail()
                    , createCompanyUserCommand.getPhone(), createCompanyUserCommand.getPurpose(), createCompanyUserCommand.getStatus()));
    }

    @EventSourcingHandler
    protected void on(CCpCreateCompanyUserEvent cCpCreateCompanyUserEvent){
        this.ccpId=cCpCreateCompanyUserEvent.getCcpId();
        this.userId = cCpCreateCompanyUserEvent.getUserId();
        this.firstName = cCpCreateCompanyUserEvent.getFirstName();
        this.lastName = cCpCreateCompanyUserEvent.getLastName();
        this.email = cCpCreateCompanyUserEvent.getEmail();
        this.phone = cCpCreateCompanyUserEvent.getPhone();
        this.purpose = cCpCreateCompanyUserEvent.getPurpose();
        this.status = cCpCreateCompanyUserEvent.getStatus();
        log.info("Company User Crated by CCP");

    }

    @EventSourcingHandler
    protected void on(CCpCreateCompanyUserOnFaliureEvent cCpCreateCompanyUserOnFaliureEvent){
        this.ccpId=cCpCreateCompanyUserOnFaliureEvent.getCcpId();
        this.userId = cCpCreateCompanyUserOnFaliureEvent.getUserId();
        this.status = cCpCreateCompanyUserOnFaliureEvent.getStatus();
        log.info("ROLL BACKING CHANGES");

    }
    @CommandHandler
    public void on( CallingCcpCompanyCommand cmd) {
        log.info (" Inside CallingCcpCompany Command Handler");
        AggregateLifecycle.apply(new CallingEntCompanyEvent (cmd.getFirstName(),cmd.getLastName(),
                cmd.getEmail(),cmd.getPhone(),cmd.getPurpose(),cmd.getStatus(),cmd.getUserId ()));
    }

    @EventSourcingHandler
    public void on( CallingCcpCompanyEvent event){
        this.userId = event.getUserId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.purpose = event.getPurpose();
        this.status = event.getStatus();
        log.info ( "Publishing the event to call CCP" );
    }
}

