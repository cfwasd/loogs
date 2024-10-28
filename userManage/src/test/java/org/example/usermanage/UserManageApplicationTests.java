package org.example.usermanage;

import org.example.usermanage.Service.Impl.UserServiceImpl;
import org.example.usermanage.query.PageQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
@Component
class UserManageApplicationTests {

    @Autowired
    UserServiceImpl userService;
    @Test
    void contextLoads() {
        //分页查询
        PageQuery pageQuery = new PageQuery(1,10,"created_at",true);
        System.out.printf(pageQuery.toString());
//        System.out.println(userService.selectByPage(pageQuery).toString());

    }

}
