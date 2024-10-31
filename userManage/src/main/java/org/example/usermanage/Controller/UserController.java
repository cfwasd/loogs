package org.example.usermanage.Controller;

import com.example.common.ResponseResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.usermanage.Service.Impl.UserServiceImpl;
import org.example.usermanage.dto.LoginModel;
import org.example.usermanage.dto.RegisterModel;
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
    public List<Users> selectByPage(@RequestBody PageQuery query) {
        return userService.selectByPage(query);
    }
    //登录
    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginModel loginModel) {
        return userService.login(loginModel);
    }
    //注册
    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisterModel registerModel) {
        return userService.register(registerModel);
    }
}
