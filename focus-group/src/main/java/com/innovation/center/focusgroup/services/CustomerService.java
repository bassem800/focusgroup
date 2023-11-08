package com.innovation.center.focusgroup.services;

import com.innovation.center.focusgroup.ApplicationException;
import com.innovation.center.focusgroup.entities.Customer;
import com.innovation.center.focusgroup.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer createUser(Customer customer) {
        // Implement the logic for creating a customer
        return customerRepository.save(customer);
    }

    public Customer getUserById(Long userId) {
        // Implement the logic for retrieving a customer by ID
        return customerRepository.findById(userId).orElse(null);
    }

    public List<Customer> getAllUsers() {
        // Implement the logic for retrieving all users
        return customerRepository.findAll();
    }

    public Customer updateUser(Customer customer) {
        // Implement the logic for updating a customer
        // You can add validation and error handling here
        return customerRepository.save(customer);
    }

    public void deleteUser(Long userId) {
        // Implement the logic for deleting a customer by ID
        customerRepository.deleteById(userId);
    }

    public Customer registerUser(Customer customer) {
        // Check if the username already exists
        if (customerRepository.findByUsername(customer.getUsername()) != null) {
            throw new ApplicationException.UsernameAlreadyExistsException("Username already exists");
        }

        // Encode the customer's password before saving it
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        // Save the customer to the database
        return customerRepository.save(customer);
    }

    public Customer loginUser(String username, String password) {
        Customer existingUser = customerRepository.findByUsername(username);

        if (existingUser == null) {
            throw new ApplicationException.UserNotFoundException("Customer not found");
        }

        if (passwordEncoder.matches(password, existingUser.getPassword())) {
            return existingUser; // Successful login
        } else {
            throw new ApplicationException.InvalidPasswordException("Invalid password");
        }
    }
    // Other customer-related methods as needed

}