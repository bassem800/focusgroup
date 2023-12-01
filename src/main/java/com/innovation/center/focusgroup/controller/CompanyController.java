package com.innovation.center.focusgroup.controller;

import com.innovation.center.focusgroup.entities.Company;
import com.innovation.center.focusgroup.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company createdCompany = companyService.createCompany(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        return ResponseEntity.ok(company);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Company>> searchCompaniesByName(@RequestParam(required = false) String companyName) {
        List<Company> companies = companyService.searchCompaniesByName(companyName);
        return ResponseEntity.ok(companies);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long companyId, @RequestBody Company company) {
        company.setId(companyId);
        Company updatedCompany = companyService.updateCompany(company);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Company> registerCompany(@RequestBody Company company) {
        Company registeredCompany = companyService.registerCompany(company);
        return new ResponseEntity<>(registeredCompany, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Company> loginCompany(@RequestParam String email, @RequestParam String password) {
        Company loggedInCompany = companyService.loginCompany(email, password);
        return ResponseEntity.ok(loggedInCompany);
    }
}
