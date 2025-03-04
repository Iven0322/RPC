package com.iven.ivenrpc.registry;


import com.iven.ivenrpc.model.ServiceMetaInfo;

import java.util.List;

//服务注册中心本地缓存
public class RegistryServiceCache {
    //服务缓存
    List<ServiceMetaInfo> serviceCache;

    //写缓存
    void writeCache(List<ServiceMetaInfo> newServiceCache) {
        this.serviceCache = newServiceCache;
    }

    //读缓存
    List<ServiceMetaInfo> readCache() {
        return this.serviceCache;
    }

    //清空缓存
    void clearCache() {
        this.serviceCache = null;
    }
}
