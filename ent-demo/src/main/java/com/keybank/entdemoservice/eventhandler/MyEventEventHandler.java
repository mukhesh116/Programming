package com.keybank.entdemoservice.eventhandler;


import com.keybank.coreapi.cx.commands.CallingCcpCompanyCommand;
import com.keybank.coreapi.cx.commands.EntCreateCompanyUserCommand;
import com.keybank.coreapi.cx.commands.HandingOverToAggregatorCommand;
import com.keybank.coreapi.cx.events.CallingEntCompanyEvent;
import com.keybank.coreapi.cx.events.CreateUserEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/*
 If ProcessingGroup is not set, then the package name is used.
 The name of the ProcessingGroup is also in the configuration
 and the name must match the name of the TrackingProcessor defined
 in the configuration. Axon adds automatically all the handler to the TrackingProcessor
 The class must be a @Component
 */
@Component
@ProcessingGroup("MyProcessor")
@Slf4j
public class MyEventEventHandler {		
	
	@Autowired
	private transient CommandGateway commandGateway;


	
	@EventHandler
	public void handleMyEvent(CallingEntCompanyEvent event) {
		log.info("\n\ngot the CallingEntCompanyEvent event {}" + event);
		String entId = UUID.randomUUID().toString();
		EntCreateCompanyUserCommand command =  new EntCreateCompanyUserCommand(entId, event.getUserId(), event.getFirstName(), event.getLastName(),
				event.getEmail(), event.getPhone(), event.getPurpose(), event.getStatus());

		log.info ("CallingEntCompanyEvent ==> "+command);
		commandGateway.send ( command, new CommandCallback< EntCreateCompanyUserCommand, Object > ( ) {
            @Override
            public void onSuccess ( CommandMessage< ? extends EntCreateCompanyUserCommand > commandMessage, Object result ) {

            	HandingOverToAggregatorCommand command1 = new HandingOverToAggregatorCommand ( event.getUserId(), event.getFirstName(), event.getLastName(),
                        event.getEmail(), event.getPhone(), event.getPurpose(), event.getStatus());

                commandGateway.send(command1);
            }

            @Override
            public void onFailure ( CommandMessage< ? extends EntCreateCompanyUserCommand > commandMessage, Throwable cause ) {

                log.info ( "Ent Service Failed Roll Back the Changes" );
            }
        } );		
	}

}
