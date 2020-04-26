package com.mk.payrate.service;

import com.mk.payrate.io.Response;
import com.mk.payrate.model.Employee;
import com.mk.payrate.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class EmployeeService {


    @Autowired
    EmployeeRepo repository;

    public String authenticate(String userName, String passWord) {

        return null;
    }

    public Response getAll() {
      List<Employee> data=repository.findAll();
        return  Response.builder().status("Successes").systemMessage("Record saved successfully").data(data).build();
    }

    public Response create(List<Employee> employees) {
        List dataArray = new ArrayList();
        for (Employee employee :employees) {
            long actualSalary = (employee.getPayHours() * employee.getPayRate());
            long salary = actualSalary - ((employee.getPercentageCut()/100)*actualSalary);
            long finalSalary = salary - employee.getCreditedSalary();
            employee.setFinalSalary(finalSalary);
            Employee employee1 = repository.save(employee);
            dataArray.add(employee1);
        }
        return Response.builder().status("Successes").systemMessage("Record saved successfully").data(dataArray).build();
    }

    public Response deleteById(long id) {
        Optional<Employee> employee = repository.findById((int) id);
        if (employee.isPresent()) {
            repository.deleteById((int) id);
            return Response.builder().status("Successes").systemMessage("Record deleted successfully").build();
        }
        return Response.builder().status("Failed").systemMessage("Record Not found").build();
    }

}
