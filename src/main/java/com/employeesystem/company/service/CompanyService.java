package com.employeesystem.company.service;

import com.employeesystem.company.Company;
import com.employeesystem.company.repository.CompanyRepository;
import com.employeesystem.employee.Employee;
import com.employeesystem.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompanyService {


    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public Company createNewCompany(Company company){
        return companyRepository.save(company);
    }

    public List<Company> showAllCompanies(){
        return companyRepository.findAll();
    }

    public List<Company> sortCompaniesByName(){
        return companyRepository.findAll().stream()
                .sorted(Comparator.comparing(Company::getName))
                .collect(Collectors.toList());
    }

    public void addEmployeeToCompany(Long employeeId, Long companyId) {
        if (companyRepository.existsById(companyId) && employeeRepository.existsById(employeeId)) {
            Employee employee = employeeRepository.findById(employeeId).get();
            Company company = companyRepository.findById(companyId).get();
            employee.setCompany(company);
            employeeRepository.save(employee);
            company.getEmployees().add(employee);
            companyRepository.save(company);
        } else
        System.out.println("Employee or company doesn't exist");

    }

    public Company findCompanyById(Long id){
        Optional<Company> company = companyRepository.findById(id);
        return company.orElse(null);
    }

    public void deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
    }

}
