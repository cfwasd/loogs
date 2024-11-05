package org.example.usermanage.Service.Impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.HttpStateCode;
import com.example.common.JWTUtils;
import com.example.common.ResponseResult;
import org.example.usermanage.Mapper.UserMapper;
import org.example.usermanage.Service.UserService;
import org.example.usermanage.entity.Users;
import org.example.usermanage.query.PageQuery;
import org.example.usermanage.utils.WxLoginHttps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    WxLoginHttps wxLoginHttps = new WxLoginHttps();

    //根和userID查询信息
    public Users selectByUserId(int userId) {
        return userMapper.selectByUserId(userId);
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
    public ResponseResult login(String code) {
        if (("").equals(code)){
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "参数错误", null);
        }
        String openId = wxLoginHttps.wiLog(code).getWxlogin().getOpenid();
        Users user = userMapper.selectByOpenId(openId);
        if (user==null) {
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "用户不存在", null);
        }else {
            Map<String,String> logToken = new HashMap<>();
            logToken.put("userId",user.getUserId().toString());
            logToken.put("academy",user.getAcademy());
            String token = JWTUtils.getToken(logToken);
            if (setToken(token,"user")){
                System.out.println(user);
                Map<String,String> map = new HashMap<>();
                map.put("token",token);
                map.put("openId",openId);
                return new ResponseResult(HttpStateCode.OK.getCode(), "登陆成功", map);
            }
            return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "服务器异常，请稍后", null);
        }

    }
    //用户注册
    @Override
    public ResponseResult register(Users users) {
        if (users==null){
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "参数错误", null);
        }
        String openId = users.getOpenId();
        if(users.getOpenId()==null){

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

            Map<String,String> logToken = new HashMap<>();
//            logToken.put("userId",users.getUserId().toString());
//            logToken.put("academy",users.getAcademy());
            logToken.put("openid",openId);
            String token = JWTUtils.getToken(logToken);
            Map<String,String> map = new HashMap<>();
            map.put("token",token);
            map.put("openId",openId);
            return new ResponseResult(HttpStateCode.OK.getCode(), "注册成功",map);
        }
    }

    //根据openId查询用户
    public ResponseResult selectByOpenId(String openId) {
        if (("").equals(openId)){
            return new ResponseResult(HttpStateCode.BAD_REQUEST.getCode(), "参数错误", null);
        }
        if(openId!=null){
            Users user = userMapper.selectByOpenId(openId);
            return new ResponseResult(HttpStateCode.OK.getCode(), "查询成功", user);
        }
        return new ResponseResult(HttpStateCode.INTERNAL_SERVER_ERROR.getCode(), "服务端错误", null);
    }

    public boolean setToken(String token, String type){
        try {
            redisTemplate.opsForValue().set(token,type,7,java.util.concurrent.TimeUnit.DAYS);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
