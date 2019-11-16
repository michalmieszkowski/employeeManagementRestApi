package com.employeesystem.employee;

import com.employeesystem.company.Company;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private Long identity;

    private String name;

    private String surname;

    private Sex sex;

    private Float salary;

    private Date startWork;

    private Boolean criminalRecord = false;

    private Boolean archive = false;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "company_id", nullable = false)
    @JsonBackReference
    private Company company;

}
