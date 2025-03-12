package com.iven.ivenrpc.loadbalancer;

import com.iven.ivenrpc.spi.SpiLoader;

public class LoadBalancerFactory {
    static{
        SpiLoader.load(LoadBalancer.class);
    }

    //默认复杂均衡器
    private static final LoadBalancer DEFAULT_LOAD_BALANCER =  new ConsistentHashLoadBalancer();

    //获取实例
    public static LoadBalancer getInstance(String key){
        return SpiLoader.getInstance(LoadBalancer.class,key);
    }
}
