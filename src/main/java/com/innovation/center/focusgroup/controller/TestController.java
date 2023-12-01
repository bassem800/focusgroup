package com.innovation.center.focusgroup.controller;


import com.innovation.center.focusgroup.services.TestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@CrossOrigin("*")
public class TestController {

    @Autowired
    TestService testService;
    @GetMapping("/healthcheck")
    @ApiOperation(value= "verify if the  controller is functional")
    public String healthcheck (){
        return  testService.healthCheck();
    }
}
