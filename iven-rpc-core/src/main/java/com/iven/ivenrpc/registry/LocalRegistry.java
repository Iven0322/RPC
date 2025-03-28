package com.iven.ivenrpc.registry;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

//本地注册中心
public class LocalRegistry {
    //注册信息存储
    private static final Map<String,Class<?>> map = new ConcurrentHashMap<String, Class<?>>();
    //注册服务
    public static  void register(String serviceName, Class<?> implClass) {
        map.put(serviceName, implClass);
    }
    //获取服务
    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }
    //删除服务
    public static void remove(String serviceName) {
        map.remove(serviceName);
    }
}
