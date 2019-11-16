package com.employeesystem.employee.controller;

import com.employeesystem.employee.Employee;
import com.employeesystem.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ResponseBody
    public List<Employee> getAllEmployees(@RequestParam (value = "sortByStartWork", required = false) Boolean sortByStartWork ){
        if (null == sortByStartWork) {
            return employeeService.showAllEmployees();
        }
        return employeeService.sortByDate();
    }

    @GetMapping ("/{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.findById(employeeId);
    }

    @PostMapping
    public Employee addNewEmployee (@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployee(employeeId);
    }

    @PutMapping ("/{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
        return employeeService.updateEmployee(employeeId, employee);
    }

    @GetMapping("/archive")
    public List<Employee> showArchive(){
        return employeeService.showArchive();
    }

    @PutMapping ("/{employeeId}/archive")
    public Employee addEmployeeToArchive(@PathVariable Long employeeId){
        return employeeService.addEmployeeToArchive(employeeId);
    }





}
