package org.example.usermanage.Service;

import com.example.common.ResponseResult;
import org.example.usermanage.dto.LoginModel;
import org.example.usermanage.dto.RegisterModel;
import org.example.usermanage.entity.Users;

public interface UserService {
    boolean insert(Users users);
    ResponseResult login(LoginModel loginModel);
    ResponseResult register(RegisterModel registerModel);
}
