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
import org.example.usermanage.dto.RegisterModel;
import org.example.usermanage.entity.Users;
import org.example.usermanage.query.PageQuery;
import org.example.usermanage.utils.WxLoginHttps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
    @Override
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
        Page<Users> users = userMapper.selectPage(userpage,lambdaQuery());
        return users.getRecords();
    }
    //用户登陆
    @Override
    public ResponseResult login(String openId) {
        if (("").equals(openId)){
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "参数错误", null);
        }
        Users user = userMapper.selectOne(new QueryWrapper<Users>().eq("open_id",openId));
        if (user==null) {
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "用户名不存在", null);
        }else {
            Map<String,String> logToken = new HashMap<>();
            logToken.put("userId",user.getUserId().toString());
            logToken.put("academy",user.getAcademy());
            String token = JWTUtils.getToken(logToken);
            return new ResponseResult(HttpStateCode.OK.getCode(), "登陆成功", token);
        }

    }
    //用户注册
    @Override
    public ResponseResult register(Users users) {
        String openId = users.getOpenId();
        if (users==null){
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "参数错误", null);
        }

        if(users.getOpenId()==null){
            WxLoginHttps wxLoginHttps = new WxLoginHttps();
            users.setOpenId(wxLoginHttps.wiLog(users.getCode()).getWxlogin().getOpenid());
            openId = users.getOpenId();
        }
        Users user = userMapper.selectByOpenId(openId);
        if(user!=null){
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "该微信已绑定了用户，直接登陆即可", null);
        }
        Users stuIs = userMapper.selectByStudentId(users.getStudentId());
        if(stuIs!=null){
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "该学号已绑定了微信", null);
        }
        int i = userMapper.insertUsers(users);
        if(i==0){
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "注册失败", null);
        }else {
            return new ResponseResult(HttpStateCode.OK.getCode(), "注册成功", users.getOpenId());
        }

    }
}
