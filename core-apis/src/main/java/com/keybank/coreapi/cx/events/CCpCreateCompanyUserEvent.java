package com.keybank.coreapi.cx.events;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CCpCreateCompanyUserEvent {

    private String ccpId;

     private String userId;

     private String firstName;

     private String lastName;

     private String email;

     private String phone;

     private String purpose;

     private String status;

    public CCpCreateCompanyUserEvent() {
    }

    public CCpCreateCompanyUserEvent(String ccpId, String userId, String firstName, String lastName, String email, String phone, String purpose, String status) {
        this.ccpId=ccpId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.purpose = purpose;
        this.status = "CREATED CCP";
    }

//
//    public void onSuccess( String commandMessage, String result) {
//        log.debug("CompanyUser Created");
//
//    }
//
//    public void onFailure(String commandMessage, Throwable cause) {
//        log.error("CompanyUser Failed", cause);
//    }


}
