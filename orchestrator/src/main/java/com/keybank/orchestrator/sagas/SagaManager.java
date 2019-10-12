package com.keybank.orchestrator.sagas;

import com.keybank.coreapi.cx.commands.CallingCcpCompanyCommand;
import com.keybank.coreapi.cx.commands.CallingEntCompanyCommand;
import com.keybank.coreapi.cx.events.CreateUserEvent;
import com.keybank.coreapi.cx.events.HandingOverToAggregatorEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.SagaLifecycle;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Data
@Saga(configurationBean="mySagaConfiguration")
@ProcessingGroup ("AggregatorProcessor")
@Component
public class SagaManager {

    @Autowired
    private transient CommandGateway commandGateway;

    /*
  Calling ENT service EVENTS
   */
    @ StartSaga
    @SagaEventHandler (associationProperty = "userId")
    public void handle(CreateUserEvent createUserEvent) {
        log.info("Saga Life Started");
        log.info("Calling Ent service");
        CallingEntCompanyCommand cmd =  new CallingEntCompanyCommand (createUserEvent.getUserId(), createUserEvent.getFirstName(), createUserEvent.getLastName(),
                createUserEvent.getEmail(), createUserEvent.getPhone(), createUserEvent.getPurpose(), createUserEvent.getStatus());
         commandGateway.send (cmd);
        SagaLifecycle.associateWith ("userId", createUserEvent.getUserId ());
    }
    /*
    Calling CCP service EVENTS
     */
    @EndSaga
    @SagaEventHandler (associationProperty = "userId")
    public void handleMyEvent(HandingOverToAggregatorEvent event) {
        log.info("Calling CCp service");
        log.info("Came  Inside  Saga");
        String ccpId = UUID.randomUUID().toString();
        CallingCcpCompanyCommand cmd =  new CallingCcpCompanyCommand (event.getUserId(), event.getFirstName(), event.getLastName(),
                event.getEmail(), event.getPhone(), event.getPurpose(), event.getStatus());
        commandGateway.send (cmd);

    }




}
