package com.iven.ivenrpc.fault.tolerant;

import com.iven.ivenrpc.model.RpcRequest;
import com.iven.ivenrpc.model.RpcResponse;

import java.util.Map;

public interface TolerantStrategy {

    //容错
    RpcResponse doTolerant(Map<String,Object> context,Exception e);
}
