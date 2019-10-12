package com.keybank.ccpdemoservice.eventhandler;


import com.keybank.coreapi.cx.commands.CcpCreateCompanyUserCommand;
import com.keybank.coreapi.cx.events.CCpCreateCompanyUserEvent;
import com.keybank.coreapi.cx.events.CallingCcpCompanyEvent;
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
@ProcessingGroup("CcpProcessor")
@Slf4j
public class MyEventEventHandler {		
	
	@Autowired
	private transient CommandGateway commandGateway;
	
	@EventHandler
	public void handleMyEvent( CallingCcpCompanyEvent event) {
		log.info("got the CallingCcpCompanyEvent event {}" + event);
		String ccpId = UUID.randomUUID().toString();
		CcpCreateCompanyUserCommand command =  new CcpCreateCompanyUserCommand(ccpId, event.getUserId(), event.getFirstName(), event.getLastName(),
				event.getEmail(), event.getPhone(), event.getPurpose(), event.getStatus());

		log.info ("CcpCompanyEvent ==> "+command);
		commandGateway.send ( command, new CommandCallback< CcpCreateCompanyUserCommand, Object > ( ) {
			@Override
			public void onSuccess ( CommandMessage< ? extends CcpCreateCompanyUserCommand > commandMessage, Object result ) {
				log.info ("User Crated sucessfully in ccp");
			}

			@Override
			public void onFailure ( CommandMessage< ? extends CcpCreateCompanyUserCommand > commandMessage, Throwable cause ) {
				log.info ("User Crated Failed in ccp");
			}
		});
	}

}
