package org.example.usermanage.dto;

import lombok.Data;

@Data
public class RegisterModel {
    private String username;
    private String email;
    private String password;
    private String studentId;
}
