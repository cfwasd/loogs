package org.example.usermanage.dto;

import lombok.Data;

@Data
public class RegisterModel {
    private String userName;
    private String email;
    private String password;
    private String studentId;
}
