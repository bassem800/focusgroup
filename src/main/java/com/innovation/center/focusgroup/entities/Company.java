package com.innovation.center.focusgroup.entities;

import lombok.Getter;


import javax.persistence.*;
import java.util.List;

@Getter
@Entity


public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String companyName;

    @Column(nullable = false, length = 45)
    private String contactPerson;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    private String domain;

    @Column(nullable = false, length = 64)
    private String password;

    private boolean isActivated=true;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Survey> surveys;

    // Constructors, getters, and setters

    public Company() {
        // Default constructor
    }

    public Company(String companyName, String contactPerson, String email, String domain, boolean isActivated) {
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.email = email;
        this.domain = domain;
        this.isActivated = isActivated;
    }

    // Getters and setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    // Additional getters and setters for other fields

    // toString, equals, and hashCode methods (optional)

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", email='" + email + '\'' +
                ", domain='" + domain + '\'' +
                ", isActivated=" + isActivated +
                '}';
    }


    // Add other methods or annotations as needed
}
