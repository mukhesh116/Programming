package com.keybank.coreapi.cx.commands;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.TargetAggregateIdentifier;


@Slf4j
@Data
public class CreateUserCommand {

    @TargetAggregateIdentifier
    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String purpose;

    private String status;

    public CreateUserCommand() {
    }

    public CreateUserCommand(String userId, String firstName, String lastName, String email, String phone, String purpose, String status) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.purpose = purpose;
        this.status = status;
    }
}
