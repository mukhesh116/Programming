package com.keybank.orchestrator.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class User {

    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String purpose;

    private String status;

}
