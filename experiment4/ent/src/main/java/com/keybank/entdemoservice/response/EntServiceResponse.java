package com.keybank.entdemoservice.response;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
public class EntServiceResponse {

    String name;
    String status;
}
