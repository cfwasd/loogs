package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class stepCountVO {
    String code;
    String encryptedData;
    String iv;

    public stepCountVO() {
    }
}
