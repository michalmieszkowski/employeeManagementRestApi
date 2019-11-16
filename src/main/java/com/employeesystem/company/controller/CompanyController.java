package com.employeesystem.company.controller;

import com.employeesystem.company.Company;
import com.employeesystem.company.service.CompanyService;
import com.employeesystem.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/company")
public class CompanyController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;

    @Autowired
    public CompanyController(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    @GetMapping
    @ResponseBody
    public List<Company> getAllCompanies (@RequestParam (value = "sortByName", required = false) Boolean sortByName) {
        if (null == sortByName) {
            return companyService.showAllCompanies();
        }
        return companyService.sortCompaniesByName();
    }

    @GetMapping("/{companyId}")
    public Company getCompanyById(@PathVariable Long companyId) {
        return companyService.findCompanyById(companyId);
    }

    @PostMapping
    public Company addNewCompany(@RequestBody Company company) {
        return companyService.createNewCompany(company);
    }

    @PutMapping("/{companyId}/employee/{employeeId}")
    public void addEmployeeToCompany(@PathVariable Long companyId, @PathVariable Long employeeId){
        companyService.addEmployeeToCompany(employeeId, companyId);
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompanyById(@PathVariable Long companyId) {
        companyService.deleteCompanyById(companyId);
    }

}
