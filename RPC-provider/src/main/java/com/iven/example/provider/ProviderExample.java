package com.iven.example.provider;


import com.iven.example.common.service.UserService;
import com.iven.ivenrpc.RpcApplication;
import com.iven.ivenrpc.config.RegistryConfig;
import com.iven.ivenrpc.config.RpcConfig;
import com.iven.ivenrpc.model.ServiceMetaInfo;
import com.iven.ivenrpc.registry.LocalRegistry;
import com.iven.ivenrpc.registry.Registry;
import com.iven.ivenrpc.registry.RegistryFactory;
import com.iven.ivenrpc.server.HttpServer;
import com.iven.ivenrpc.server.VertexHttpServer;

public class  ProviderExample {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        HttpServer httpServer = new VertexHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
