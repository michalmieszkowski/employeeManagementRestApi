package com.employeesystem.company.repository;

import com.employeesystem.company.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CompanyRepository extends CrudRepository <Company, Long> {


        Company save(Company company);

        List<Company> findAll();

        Optional<Company> findById(Long id);

        boolean existsById(Long id);

        void deleteById(Long id);

    }


