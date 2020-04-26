package com.mk.payrate.io;

import com.mk.payrate.model.Employee;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Response {

    private String status;
    private String systemMessage;
    private List<Employee> data;

}
