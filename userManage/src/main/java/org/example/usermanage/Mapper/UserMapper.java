package org.example.usermanage.Mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.usermanage.entity.Users;

/**
 * @author wzh
 */
@Mapper
public interface UserMapper extends BaseMapper<Users> {
    //新增用户
    @Insert("insert into users(student_id,academy,clasz,user_name,nickname,open_id) " +
            "values (#{studentId},#{academy},#{clasz},#{userName},#{nickname},#{openId})")
    public int insertUsers(Users users);

    //根据openId查询用户
    @Select("select * from users where open_id = #{openId}")
    public Users selectByOpenId(String openId);

    //根据学号查询用户
    @Select("select * from users where student_id = #{studentId}")
    public Users selectByStudentId(String studentId);
}
