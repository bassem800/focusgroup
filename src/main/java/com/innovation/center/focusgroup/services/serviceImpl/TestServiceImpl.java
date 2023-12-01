package com.innovation.center.focusgroup.services.serviceImpl;

import com.innovation.center.focusgroup.services.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {


    @Override
    public String healthCheck() {
        return "test is available";
    }
}
