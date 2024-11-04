package org.example.usermanage;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.usermanage.utils.WxLoginHttps;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wzh
 */

@SpringBootTest
public class UsersManageApplicationTest {

    private WxLoginHttps wxLoginUtils = new WxLoginHttps();
    @Test
    public void contextLoads() throws JsonProcessingException {
        wxLoginUtils.wiLog("0e3bIhll2R6Sre4tHbol2fp0tC2bIhlj");
    }

}
