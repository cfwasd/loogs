package org.example.usermanage.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.usermanage.Mapper.UserMapper;
import org.example.usermanage.Service.UserService;
import org.example.usermanage.entity.Users;
import org.example.usermanage.query.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.Wrappers.lambdaQuery;

/**
 * @author wzh
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    //根和userID查询信息
    public Users selectByUserId(String userId)
    {
        return userMapper.selectOne(new QueryWrapper<Users>().eq("user_id",userId));
    }
    //新增数据
    public boolean insert(Users users)
    {
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
}
