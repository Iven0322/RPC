package com.iven.ivenrpc.registry;

import com.iven.ivenrpc.spi.SpiLoader;


public class RegistryFactory {

    static {
        SpiLoader.load(Registry.class);
    }
    //默认注册中心
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    //获取实例

    public static Registry  getInstance(String key){
        return SpiLoader.getInstance(Registry.class,key);
    }
}
