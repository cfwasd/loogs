package com.example.runningmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wzh
 */
@SpringBootApplication
public class RunningManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunningManagerApplication.class, args);
    }

}
