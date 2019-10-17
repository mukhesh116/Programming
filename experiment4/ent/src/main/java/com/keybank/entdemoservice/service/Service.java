package com.keybank.entdemoservice.service;

import com.keybank.entdemoservice.response.EntServiceResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
public class Service {


    @Autowired
    EntServiceResponse entServiceResponse;


    public EntServiceResponse create(String name) {

        entServiceResponse.setName ( name );

        if ( Math.random ( ) >= 0.8 ) {
            log.info ( "    Ent Throws Error and service Failed " );

            entServiceResponse.setStatus ( "Ent Service Failed Plz try Again later" );

            throw new RuntimeException ( "Random failure during Ent" );
        }

            entServiceResponse.setStatus ( "User Created Successfully in Ent Service" );

            return entServiceResponse;
    }



    public EntServiceResponse delete(String name) {

        entServiceResponse.setName ( name );

        if ( Math.random ( ) >= 0.8 ) {
            log.info ( "    Ent Throws Error and service Failed " );

            entServiceResponse.setStatus ( "Ent Service Failed Plz try Again later" );

            throw new RuntimeException ( "Random failure during Ent" );
        }

        entServiceResponse.setStatus ( "User Deleted Successfully in Ent Service" );

        return entServiceResponse;
    }


}
