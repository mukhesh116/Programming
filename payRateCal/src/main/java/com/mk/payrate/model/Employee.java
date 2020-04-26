package com.mk.payrate.model;

import lombok.Data;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEE")
@Data
public class Employee {

    @Column(name = "Id")
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id;

    @Column(name = "Name")
    String name;

    @Column(name = "FromDate")
    private Date fromDate;

    @Column(name = "ToDate")
    private Date toDate;

    @Column(name = "PayHours")
    private long payHours;

    @Column(name = "PayRate")
    private long payRate;

    @Column(name = "PercentageCut")
    private int percentageCut;

    @Column(name = "CreditedSalary")
    private long creditedSalary;

    @Column(name = "FinalSalary")
    private long finalSalary;

    @Column(name = "DifferenceSalary")
    private long differenceSalary;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "PassWord")
    private String passWord;

}
