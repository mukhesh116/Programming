package com.keybank.orchestrator;


import com.keybank.coreapi.cx.commands.CallingCcpCompanyCommand;
import com.keybank.coreapi.cx.commands.EntCreateCompanyUserCommand;
import com.keybank.coreapi.cx.events.CallingCcpCompanyEvent;
import com.keybank.coreapi.cx.events.CallingEntCompanyEvent;
import com.keybank.coreapi.cx.events.HandingOverToAggregatorEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.GenericEventMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@ProcessingGroup("AggregatorProcessor")
@Slf4j
public class MyEventEventHandler {

	@Autowired
	private transient CommandGateway commandGateway;

	@Autowired
	private EventBus eventBus;

	@EventHandler
	public void handle( HandingOverToAggregatorEvent event) {
		log.info("\n\ngot the CallingCcpCompanyEvent event {}" + event);
		eventBus.publish( GenericEventMessage.asEventMessage(new HandingOverToAggregatorEvent (event.getUserId (),
				event.getFirstName (),event.getLastName (),event.getEmail (),event.getPhone (),event.getPurpose (),event.getStatus () )));
	}




}
