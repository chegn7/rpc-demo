package com.c.rpc;

import lombok.Data;

/**
 * RPC响应
 */
@Data
public class Response {
    /**
     * code 0 相应成功，其它相应失败
     * msg 成功/失败详情
     * data 响应数据
     */
    private int code = 0;
    private String msg = "ok";
    private Object data;
}
