package com.example.runningmanager;

import com.example.runningmanager.service.Impl.RunningServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

/**
 * @author wzh
 */
@SpringBootTest
@Component
public class RunningManagerApplicationTest {
    @Autowired
    public RunningServiceImpl runningService;
    @Test
    public void test()
    {
        System.out.println(runningService.selectByUserId(1).getData().toString());
    }

}
