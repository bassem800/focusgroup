package com.innovation.center.focusgroup;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*", maxAge = 3600)
public class FocusGroupApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.innovation.center.focusgroup.FocusGroupApplication.class, args);
    }
}
