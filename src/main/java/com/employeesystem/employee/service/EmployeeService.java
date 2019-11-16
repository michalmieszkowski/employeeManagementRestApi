package com.employeesystem.employee.service;

import com.employeesystem.employee.Employee;
import com.employeesystem.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> showAllEmployees() {
        List<Employee> activeEmployees = employeeRepository.findAll().stream()
                .filter(employee -> employee.getArchive() == false)
                .collect(Collectors.toList());
        return activeEmployees;
    }

    public List<Employee> showArchive() {
        List<Employee> archivedEmployees = employeeRepository.findAll().stream()
                .filter(Employee::getArchive)
                .collect(Collectors.toList());
        return archivedEmployees;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee findById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

    public List<Employee> sortByDate() {
        List<Employee> sorted = showAllEmployees().stream()
                .sorted(Comparator.comparing(employee -> employee.getStartWork()))
                .collect(Collectors.toList());
        return sorted;
    }

    public Employee addEmployeeToArchive(Long id) {
        Optional<Employee> employeeArchive = employeeRepository.findById(id);
        if (employeeArchive.isPresent()) {
            employeeArchive.get().setArchive(true);
            createEmployee(employeeArchive.get());
            return employeeArchive.get();
        } else
            return null;
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> employeeToUpdate = employeeRepository.findById(id);
        if (employeeToUpdate.isPresent()){
            employeeRepository.deleteById(id);
        }
        return employeeRepository.save(employee);
    }

}
