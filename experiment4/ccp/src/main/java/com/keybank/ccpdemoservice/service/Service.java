package com.keybank.ccpdemoservice.service;

import com.keybank.ccpdemoservice.response.CcpServiceResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Slf4j
public class Service {


    @Autowired
    CcpServiceResponse ccpServiceResponse;


    public CcpServiceResponse create(String name) {

        ccpServiceResponse.setName ( name );

        if ( Math.random ( ) >= 0.8 ) {
            log.info ( "    Ent Throws Error and service Failed " );

            ccpServiceResponse.setStatus ( "CCP Service Failed Plz try Again later" );

            throw new RuntimeException ( "Random failure during CCP" );
        }

        ccpServiceResponse.setStatus ( "User Created Successfully in CCP Service" );

            return ccpServiceResponse;
    }




}
