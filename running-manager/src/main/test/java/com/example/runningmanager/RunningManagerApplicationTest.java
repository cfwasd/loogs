package com.example.runningmanager;
import com.example.runningmanager.service.Impl.RunningServiceImpl;
import com.example.runningmanager.utils.WeRunDecryptUtil;
import com.example.runningmanager.utils.WeRunRedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author wzh
 */
@SpringBootTest
@Component
@ComponentScan(basePackages = {"com.example.runningmanager"})
public class RunningManagerApplicationTest {
    @Autowired
    public RunningServiceImpl runningService;
    @Autowired
    WeRunRedisUtil weRunDecryptUtil;
    @Test
    public void test()
    {
        try {
            System.out.println(weRunDecryptUtil.exchange("1","100"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
