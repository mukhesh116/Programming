package com.keybank.orchestrator.sagas;

import com.keybank.coreapi.cx.commands.aggregator.CallCcpCommand;
import com.keybank.coreapi.cx.commands.aggregator.ReturnCommand;
import com.keybank.coreapi.cx.commands.ccp.CreateUserCcpCommand;
import com.keybank.coreapi.cx.commands.ent.CreateUserEntCommand;
import com.keybank.coreapi.cx.commands.ent.DeleteUserEntCommand;
import com.keybank.coreapi.cx.events.aggregator.CallCcpEvent;
import com.keybank.coreapi.cx.events.aggregator.CallEntEvent;
import com.keybank.coreapi.cx.events.aggregator.ReturnEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import javax.inject.Inject;
import java.util.UUID;

@Slf4j
@Data
@Saga
public class SagaManager {

    @Inject
    private transient CommandGateway commandGateway;

    String entId = UUID.randomUUID ( ).toString ( );
    String ccpId = UUID.randomUUID ( ).toString ( );

    @StartSaga
    @SagaEventHandler ( associationProperty = "aggregatorId" )
    public void handle ( CallEntEvent callEntEvent ) {
        log.info ( "Saga Life Started" );
        log.info ( "Calling Ent service" );

        CreateUserEntCommand createUserEntCommand = new CreateUserEntCommand ( entId,callEntEvent.getName () );

        commandGateway.send ( createUserEntCommand,
                new CommandCallback< CreateUserEntCommand, Object > ( ) {
                    @Override
                    public void onResult ( CommandMessage< ? extends CreateUserEntCommand > commandMessage, CommandResultMessage< ? > commandResultMessage ) {
                       if(commandResultMessage.isExceptional ()){
                           log.info ( " Ent Service Failed with an Exception " );
                           callEntEvent.setStatus ( "Ent Service Failed to create Plz try again later" );
                           ReturnCommand returnCommand = new ReturnCommand (callEntEvent.getAggregatorId (),callEntEvent.getStatus (),callEntEvent.getName ()  );
                           commandGateway.send ( returnCommand );

                        }else {
                           log.info ( "Ent Service Success" );
                           CallCcpCommand callCcpCommand = new CallCcpCommand ( callEntEvent.getAggregatorId ( ), callEntEvent.getName ( ), callEntEvent.getStatus ( ) );
                           commandGateway.send ( callCcpCommand );
                       }
                    }
                } );

    }

    @SagaEventHandler ( associationProperty = "aggregatorId" )
    public void handle ( CallCcpEvent callCcpEvent ) {
        log.info ( "Calling CCp service" );
        CreateUserCcpCommand createUserCcpCommand = new CreateUserCcpCommand ( ccpId,callCcpEvent.getStatus (),callCcpEvent.getName ());
        commandGateway.send ( createUserCcpCommand,
                new CommandCallback< CreateUserCcpCommand, Object > ( ) {
                    @Override
                    public void onResult ( CommandMessage< ? extends CreateUserCcpCommand > commandMessage, CommandResultMessage< ? > commandResultMessage ) {
                        if(commandResultMessage.isExceptional ()){
                            log.info ( " CCP service failed " );
                            log.info ( "***** Now Fall Back to the compensate service that is delete the created user in Ent****" );

                            DeleteUserEntCommand deleteUserEntCommand = new DeleteUserEntCommand (entId,callCcpEvent.getName (),callCcpEvent.getStatus () );
                            commandGateway.send ( deleteUserEntCommand,
                                    new CommandCallback< DeleteUserEntCommand, Object > ( ) {
                                        @Override
                                        public void onResult ( CommandMessage< ? extends DeleteUserEntCommand > commandMessage, CommandResultMessage< ? > commandResultMessage ) {
                                            if(commandResultMessage.isExceptional ()){
                                                log.info ( "compensate delete user failed with an ExceptionEnt failed, Plz try again later" );
                                                callCcpEvent.setStatus ( "Ent Service Failed to delete Plz try again later /// Handle your own compensate method" );

                                                ReturnCommand returnCommand = new ReturnCommand ( callCcpEvent.getAggregatorId (),callCcpEvent.getStatus (),callCcpEvent.getName () );
                                                commandGateway.send ( returnCommand );
                                            }else {
                                                log.info ( "compensate Deleting user in Ent is successful" );
                                                callCcpEvent.setStatus ( "Ent Service Successfully deleted, due to failure in ccp , No creating is done in both services " );
                                                ReturnCommand returnCommand = new ReturnCommand ( callCcpEvent.getAggregatorId ( ), callCcpEvent.getStatus ( ), callCcpEvent.getName ( ) );
                                                commandGateway.send ( returnCommand );
                                            }
                                        }
                                    } );
                        }else{
                            log.info ( " Creating user in ENT and CCP is successful" );
                            callCcpEvent.setStatus ( "CCP Service Successfully created user in CCP " );
                            ReturnCommand returnCommand = new ReturnCommand ( callCcpEvent.getAggregatorId ( ), callCcpEvent.getStatus ( ), callCcpEvent.getName ( ) );
                            commandGateway.send ( returnCommand );
                        }
                    }
                } );

    }


    @EndSaga
    @SagaEventHandler ( associationProperty = "aggregatorId" )
    public void handle ( ReturnEvent returnEvent ) {
        log.info ( "Saga Life ends " );
    }


}
