package org.example.usermanage.Controller;

import com.example.common.ResponseResult;
import org.example.usermanage.Service.Impl.UserServiceImpl;
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
    @GetMapping("/login/{code}")
    public ResponseResult login(@PathVariable("code")String code ) {
        return userService.login(code);
    }
    //注册
    @PostMapping("/register")
    public ResponseResult register(@RequestBody Users users) {
        return userService.register(users);
    }
    //根据openId查询用户
    @GetMapping("/selectByOpenId/{openId}")
    public ResponseResult selectByOpenId(@PathVariable("openId")String openId) {
        return userService.selectByOpenId(openId);
    }
}
