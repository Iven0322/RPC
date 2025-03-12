package com.iven.ivenrpc.fault.retry;

import com.iven.ivenrpc.model.RpcRequest;
import com.iven.ivenrpc.model.RpcResponse;

import java.util.concurrent.Callable;

public class NoRetryStrategy implements RetryStrategy {
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
