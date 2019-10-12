package com.keybank.orchestrator.aggregate;


import com.keybank.coreapi.cx.commands.CallingCcpCompanyCommand;
import com.keybank.coreapi.cx.commands.CallingEntCompanyCommand;
import com.keybank.coreapi.cx.commands.CreateUserCommand;
import com.keybank.coreapi.cx.commands.HandingOverToAggregatorCommand;
import com.keybank.coreapi.cx.events.CallingCcpCompanyEvent;
import com.keybank.coreapi.cx.events.CallingEntCompanyEvent;
import com.keybank.coreapi.cx.events.CreateUserEvent;
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

    @AggregateIdentifier
    private String userId;

    private  String entID;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String purpose;

    private String status;

    public CreateUserAggregate() {
    }

    @CommandHandler
    public CreateUserAggregate(CreateUserCommand createUserCommand){
        AggregateLifecycle.apply(new CreateUserEvent(createUserCommand.getUserId(),
                createUserCommand.getFirstName(),createUserCommand.getLastName(),
                createUserCommand.getEmail(),createUserCommand.getPhone(),createUserCommand.getPurpose(),createUserCommand.getStatus()));
    }

    @EventSourcingHandler
    protected void on(CreateUserEvent createUserEvent){
        this.userId = createUserEvent.getUserId();
        this.firstName = createUserEvent.getFirstName();
        this.lastName = createUserEvent.getLastName();
        this.email = createUserEvent.getEmail();
        this.phone = createUserEvent.getPhone();
        this.purpose = createUserEvent.getPurpose();
        this.status = createUserEvent.getStatus();
        log.info("Company User Going to Ent");

    }

    @CommandHandler
    public void on( CallingEntCompanyCommand cmd) {
        log.info (" Inside CallingEntCompany Command Handler");
        AggregateLifecycle.apply(new CallingEntCompanyEvent (cmd.getFirstName(),cmd.getLastName(),
                cmd.getEmail(),cmd.getPhone(),cmd.getPurpose(),cmd.getStatus(),cmd.getUserId ()));
    }

    @EventSourcingHandler
    public void on(CallingEntCompanyEvent event){
        this.userId = event.getUserId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.purpose = event.getPurpose();
        this.status = event.getStatus();
        log.info ( "Publishing the event" );
    }

    @CommandHandler
    public void handle( CallingCcpCompanyCommand cmd) {
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

