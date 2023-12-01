package com.innovation.center.focusgroup.controller;


import com.innovation.center.focusgroup.ApplicationException;
import com.innovation.center.focusgroup.entities.Customer;
import com.innovation.center.focusgroup.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;


@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllUsers() {
        List<Customer> users = customerService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Customer> getUserById(@PathVariable Long userId) {
        Customer customer = customerService.getUserById(userId);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public List<Customer> searchCustomersByName(@RequestParam(required = false) String username) {
        return customerService.searchUsersByName(username);
    }

    @PostMapping
    public ResponseEntity<Customer> createUser(@RequestBody Customer customer) {
        Customer createdUser = customerService.createUser(customer);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Customer> updateUser(@PathVariable Long userId, @RequestBody Customer customer) {
        customer.setId(userId); // Set the ID from the path variable
        Customer updatedUser = customerService.updateUser(customer);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        customerService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        try {
            Customer registeredUser = customerService.registerUser(customer);
            return new ResponseEntity<>("Customer registered successfully", HttpStatus.CREATED);
        } catch (ApplicationException.UsernameAlreadyExistsException e) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        try {
            Customer customer = customerService.loginUser(username, password);
            // Successful login
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } catch (ApplicationException.UserNotFoundException e) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        } catch (ApplicationException.InvalidPasswordException e) {
            return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
        }
    }

    // Other customer-related endpoints
}
