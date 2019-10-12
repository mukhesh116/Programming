package com.keybank.coreapi.cx.events;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Getter
@Setter
public class EntCreateCompanyUserEvent {

     private  String entId;

     private String userId;

     private String firstName;

     private String lastName;

     private String email;

     private String phone;

     private String purpose;

     private String status;

    public EntCreateCompanyUserEvent() {
    }

    public EntCreateCompanyUserEvent(String entId, String userId, String firstName, String lastName, String email, String phone, String purpose, String status) {
        this.entId=entId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.purpose = purpose;
        this.status = "CREATED ENT";
    }


//
//    public void onSuccess( String commandMessage, String result) {
//        log.debug("CompanyUser Created");
//
//    }
//
//
//    public void onFailure(String commandMessage, Throwable cause) {
//        log.error("CompanyUser Failed", cause);
//    }


}
