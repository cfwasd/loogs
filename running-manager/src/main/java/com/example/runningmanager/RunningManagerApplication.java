package com.example.runningmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wzh
 */
@SpringBootApplication
@EnableScheduling
public class RunningManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunningManagerApplication.class, args);
    }

}
