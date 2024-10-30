package org.example.usermanage.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.HttpStateCode;
import com.example.common.JWTUtils;
import com.example.common.ResponseResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.usermanage.Mapper.UserMapper;
import org.example.usermanage.Service.UserService;
import org.example.usermanage.dto.LoginModel;
import org.example.usermanage.dto.RegisterModel;
import org.example.usermanage.entity.Users;
import org.example.usermanage.query.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.baomidou.mybatisplus.core.toolkit.Wrappers.lambdaQuery;

/**
 * @author wzh
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    //根和userID查询信息
    public Users selectByUserId(String userId) {
        return userMapper.selectOne(new QueryWrapper<Users>().eq("user_id",userId));
    }
    //新增数据
    public boolean insert(Users users) {
        return userMapper.insert(users)>0;
    }
    //分页查询
    public List<Users> selectByPage(PageQuery query)
    {
        //分页
        Page<Users> userpage = new Page<>(query.getPage(), query.getPageSize());
        //排序条件
        userpage.addOrder(new OrderItem(query.getSort(),query.getAsc()));
        Page<Users> Users  = userMapper.selectPage(userpage,lambdaQuery());
        return Users.getRecords();
    }

    public ResponseResult login(LoginModel loginModel) {
        Users user = userMapper.selectOne(new QueryWrapper<Users>().eq("username",loginModel.getUserName()));
        if (user==null) {
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "用户名不存在", null);
        }
        String pwd = DigestUtils.md5DigestAsHex((loginModel.getPassword() + user.getSalt()).getBytes());
        if (!user.getPassword().equals(pwd)) {
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "密码错误", null);
        }
        Map<String, String> map = new HashMap<>();
        map.put("userName",user.getUserName());
        String token = JWTUtils.getToken(map);
        return new ResponseResult(HttpStateCode.OK.getCode(), "登录成功",token);
    }
    public ResponseResult register(RegisterModel registerModel) {
        if (registerModel == null) {
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "参数错误", null);
        }
        String salt = RandomStringUtils.randomAlphanumeric(6);
        String pwd = DigestUtils.md5DigestAsHex((registerModel.getPassword() + salt).getBytes());
        Users user = new Users(registerModel.getUserName(), registerModel.getEmail(), pwd, new Date(), new Date(), registerModel.getStudentId(), salt);
        int count = 0;
        try {
            count = userMapper.insert(user);
        } catch (Exception e) {
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "服务器发生错误", null);
        }
        if (count > 0) {
            return new ResponseResult(HttpStateCode.OK.getCode(), "注册成功", null);
        }else {
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "用户名已存在", null);
        }
    }
}
