package com.innovation.center.focusgroup.repositories;

import com.innovation.center.focusgroup.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
    List<Customer> findByUsernameContainingIgnoreCase(String username);
}