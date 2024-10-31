package org.example.usermanage.Controller;

import com.example.common.ResponseResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.usermanage.Service.Impl.UserServiceImpl;
import org.example.usermanage.dto.LoginModel;
import org.example.usermanage.dto.RegisterModel;
import org.example.usermanage.entity.Users;
import org.example.usermanage.query.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/login/{id}")
    public ResponseResult login(@PathVariable("id")String id ) {
        return userService.login(id);
    }
    //注册
    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisterModel registerModel) {
        return userService.register(registerModel);
    }
    @GetMapping("/getSecret")
    public String getSecret() {
        return "appid=wxc86810d05349cc13&secret=c1462fa93713c76b51ef4b8ca9f7f4ee";
    }
}
