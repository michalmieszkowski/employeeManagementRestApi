package com.employeesystem.employee.repository;

import com.employeesystem.employee.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository <Employee, Long> {


    Employee save(Employee employee);

    List<Employee> findAll();

    void deleteById(Long id);

    Optional<Employee> findById(Long id);

    boolean existsById(Long id);


}
