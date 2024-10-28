package org.example.usermanage.Controller;

import org.example.usermanage.Service.Impl.UserServiceImpl;
import org.example.usermanage.entity.Users;
import org.example.usermanage.query.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    //分页查询
    @PostMapping("/selectByPage")
    public List<Users> selectByPage(@RequestBody PageQuery query)
    {
        return userService.selectByPage(query);
    }
}
