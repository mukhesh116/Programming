package com.keybank.coreapi.cx.events;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CCpCreateCompanyUserOnFaliureEvent {

    private String ccpId;
    private String userId;
    private String status;

    public CCpCreateCompanyUserOnFaliureEvent() {
    }

    public CCpCreateCompanyUserOnFaliureEvent(String ccpId, String userId, String status) {
        this.ccpId=ccpId;
        this.userId = userId;
        this.status = "DELETE CCP";
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
