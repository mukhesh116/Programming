package com.mk.payrate;

import com.mk.payrate.io.Response;
import com.mk.payrate.model.Employee;
import com.mk.payrate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @PostMapping(value = "/employee/auth")
    ModelAndView authenticate(@PathVariable Integer id){
        return null;
    }
    @GetMapping(value = "/employee")
    public Response getAll(){
        return service.getAll();
    }

    @PostMapping(value = "/employee")
    public Response create(@RequestBody  List<Employee> employees){
        return service.create(employees);
    }

    @DeleteMapping(value = "/employee/{id}")
    public  Response delete(@PathVariable long id) throws Exception {
        return service.deleteById(id);
    }


}
