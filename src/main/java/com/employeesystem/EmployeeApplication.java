package com.employeesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication (scanBasePackages = "com.employeesystem")
@EnableJpaRepositories //(basePackages = {"com.employeesystem.employee", "com.employeesystem.company"} )
@EntityScan //(basePackages = {"com.employeesystem.employee", "com.employeesystem.company"} )
public class EmployeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }

}
