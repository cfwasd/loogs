package com.example.runningmanager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo {
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
