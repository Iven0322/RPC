package com.iven.ivenrpc.fault.tolerant;

public interface TolerantStrategyKeys {
    //快速失败
    String FAIL_FAST = "failfast";
    //静默处理
    String FAIL_SAFE = "failsafe";
}
