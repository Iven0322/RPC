package com.iven.ivenrpc.fault.retry;

import com.iven.ivenrpc.spi.SpiLoader;

public class RetryStrategyFactory {
    static{
        SpiLoader.load(RetryStrategy.class);
    }

    //默认重试器
    private static final RetryStrategy DEFAULT_STRATEGY = new NoRetryStrategy();


    public static RetryStrategy getInstance(String key){
        return SpiLoader.getInstance(RetryStrategy.class, key);
    }
}
