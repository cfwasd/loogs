package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserNewRunDataVO {
    private String userId;
    private String stepCount;

    public UserNewRunDataVO() {
    }
}
