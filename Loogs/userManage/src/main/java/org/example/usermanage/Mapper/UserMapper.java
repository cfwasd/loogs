package org.example.usermanage.Mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.usermanage.entity.Users;

@Mapper
public interface UserMapper extends BaseMapper<Users> {

}
