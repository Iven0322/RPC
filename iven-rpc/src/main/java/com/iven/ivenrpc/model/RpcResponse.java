package com.iven.ivenrpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.PrimitiveIterator;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
//rpc响应
public class RpcResponse {
    //响应数据
    private Object data;

    //响应数据类型
    private Class<?> dataType;

    //响应信息
    private String message;

    //异常信息
    private Exception exception;
}
