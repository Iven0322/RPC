package com.iven.ivenrpc.fault.retry;


import com.iven.ivenrpc.model.RpcResponse;

import java.util.concurrent.Callable;

//重试策略
public interface RetryStrategy {


    //重试
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
