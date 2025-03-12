package com.iven.ivenrpc.fault.tolerant;

import com.iven.ivenrpc.model.RpcResponse;

import java.util.Map;

public class FailFastTolerantStrategy implements TolerantStrategy {
    @Override
    public RpcResponse doTolerant(Map<String,Object> context,Exception e){
        throw new RuntimeException("服务器报错",e);
    }
}
