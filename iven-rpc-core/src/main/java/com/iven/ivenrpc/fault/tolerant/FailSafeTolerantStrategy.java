package com.iven.ivenrpc.fault.tolerant;

import com.iven.ivenrpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;


import java.util.Map;
@Slf4j
public class FailSafeTolerantStrategy implements TolerantStrategy{

    @Override
    public RpcResponse doTolerant(Map<String,Object> context, Exception e){
        log.info("静默处理异常",e);
        return new RpcResponse();
    }
}
