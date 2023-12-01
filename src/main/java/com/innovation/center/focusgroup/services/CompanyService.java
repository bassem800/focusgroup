package com.innovation.center.focusgroup.services;

import com.innovation.center.focusgroup.ApplicationException;
import com.innovation.center.focusgroup.entities.Company;
import com.innovation.center.focusgroup.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Company createCompany(Company company) {
        // Implement the logic for creating a company
        return companyRepository.save(company);
    }

    public Company getCompanyById(Long companyId) {
        // Implement the logic for retrieving a company by ID
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Company> getAllCompanies() {
        // Implement the logic for retrieving all companies
        return companyRepository.findAll();
    }

    public List<Company> searchCompaniesByName(String companyName) {
        if (companyName == null || companyName.trim().isEmpty()) {
            // If the companyName is not provided, return all companies
            return companyRepository.findAll();
        }

        // If the username is provided, perform the search
        List<Company> companies  = companyRepository.findByCompanyNameContainingIgnoreCase(companyName);

        if (companies .isEmpty()) {
            // Throw an exception when no customers with the specified username were found
            throw new ApplicationException.UserNotFoundException("No customers found with the specified username: " + companyName);
        }

        return companies ;
    }

    public Company updateCompany(Company company) {
        // Implement the logic for updating a company
        // You can add validation and error handling here
        return companyRepository.save(company);
    }

    public void deleteCompany(Long companyId) {
        // Implement the logic for deleting a company by ID
        companyRepository.deleteById(companyId);
    }

    public Company registerCompany(Company company) {
        // Check if the email already exists
        Company existingEmailCompany = companyRepository.findByEmail(company.getEmail());
        if (existingEmailCompany != null ) {
            throw new ApplicationException.DuplicateCompanyException("Company with the same email exists");
        }

        // Encode the customer's password before saving it
        String encodedPassword = passwordEncoder.encode(company.getPassword());
        company.setPassword(encodedPassword);

        // Save the company to the database
        return companyRepository.save(company);
    }

    public Company loginCompany(String email, String password) {
        Company existingCompany = companyRepository.findByEmail(email);

        if (existingCompany != null && passwordEncoder.matches(password, existingCompany.getPassword())) {
            return existingCompany; // Successful login
        } else {
            throw new ApplicationException.UserNotFoundException("Company not found or invalid password");
        }
    }

    // Other company-related methods as needed

}
