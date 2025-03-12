package com.iven.ivenrpc.config;

import com.iven.ivenrpc.fault.retry.RetryStrategyKeys;
import com.iven.ivenrpc.fault.tolerant.TolerantStrategyKeys;
import com.iven.ivenrpc.loadbalancer.LoadBalancerKeys;
import com.iven.ivenrpc.serializer.SerializerKeys;
import lombok.Data;

//rpc配置框架
@Data
public class RpcConfig {
    //名称
    private String name = "iven-rpc";

    //版本号
    private String version ="1.0";

    //服务器主机名
    private String serverHost = "localhost";

    //服务器端口号
    private Integer serverPort = 8081;

    //序列化器
    private String serializer = SerializerKeys.JDK;

    //注册中心配置
    private RegistryConfig registryConfig = new RegistryConfig();

    //负载均衡器
    private String loadBalancer = LoadBalancerKeys.CONSISTENT_HASH;

    //重试策略
    private String retryStrategy = RetryStrategyKeys.NO;

    //容错机制
    private String tolerantStrategy = TolerantStrategyKeys.FAIL_FAST;
}
