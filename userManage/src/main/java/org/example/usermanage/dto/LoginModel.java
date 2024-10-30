package org.example.usermanage.dto;

import lombok.Data;

/**
 * @author wzh
 */
@Data
public class LoginModel {
    private String userId;
    private String userName;
    private String password;
}
