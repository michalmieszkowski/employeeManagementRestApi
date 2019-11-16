package com.employeesystem.employee.service;

import com.employeesystem.employee.Employee;
import com.employeesystem.employee.Sex;
import com.employeesystem.employee.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeServiceTest {

    @MockBean
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    private Employee employee = new Employee(1L, 11111111111L, "nameBoot", "surnameBoot", Sex.MEN, 1000f
            , new Date(2000, 1, 1), true, false, null);
    private Employee employee1 = new Employee(2L, 222222222L, "nameBoot1", "surnameBoot1", Sex.MEN, 1000f
            , new Date(2000, 2, 2), true, false, null);
    private List<Employee> employeeList = Arrays.asList(employee, employee1);


    @Test
    void when_create_Employee_should_return_Employee() {
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        Assert.assertEquals(employeeService.createEmployee(employee), employee);
    }

    @Test
    void when_showAllEmployees_should_get_List_of_Employees() {
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
        Assert.assertEquals(employeeService.showAllEmployees().size(), 2);
    }

    @Test
    void when_showArchive_should_get_List_of_Employees() {
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
        Assert.assertEquals(employeeService.showArchive().size(), 0);
    }

    @Test
    void when_findById_should_return_Employee_ifExist() {
        Long id = 1L;
        Mockito.when(employeeRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(employee));
        Assert.assertEquals(employeeService.findById(id), employee);
    }

    @Test
    void when_sortByDate_should_get_list_of_Employees_sorted() {
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
        Assert.assertEquals(employeeService.sortByDate().get(0).getId(), 1);
        Assert.assertEquals(employeeService.sortByDate().get(1).getId(), 2);
    }

    @Test
    void when_addEmployeeToArchive_should_return_true_on_Employee_getArchive() {
        Long id = 1L;
        Mockito.when(employeeRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(employee));
        Assert.assertEquals(employeeService.addEmployeeToArchive(id).getArchive(), true);
    }

    @Test
    void when_updateEmployee_should_return_Employee(){
        Long id = 2L;
        Mockito.when(employeeRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(employee1));
        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        Assert.assertEquals(employeeService.updateEmployee(id, employee), employee);
    }
}