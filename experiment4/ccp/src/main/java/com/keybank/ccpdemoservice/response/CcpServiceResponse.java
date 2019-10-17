package com.keybank.ccpdemoservice.response;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
public class CcpServiceResponse {

    String name;
    String status;
}
