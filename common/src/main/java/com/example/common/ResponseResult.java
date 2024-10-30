package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wzh
 */
@Data
@AllArgsConstructor
public class ResponseResult implements Serializable {
    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

}
