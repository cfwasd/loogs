package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpsResult {
    private int code;
    private Wxlogin wxlogin;
    private String msg;

    public HttpsResult() {
    }

}
