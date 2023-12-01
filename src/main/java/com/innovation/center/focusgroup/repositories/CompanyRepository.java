package com.innovation.center.focusgroup.repositories;

import com.innovation.center.focusgroup.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByCompanyName(String companyName);
    Company findByEmail(String email);
    List<Company> findByCompanyNameContainingIgnoreCase(String companyName);
}
