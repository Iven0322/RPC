package com.iven.example.provider;

import com.iven.example.common.service.UserService;
import com.iven.ivenrpc.RpcApplication;
import com.iven.ivenrpc.registry.LocalRegistry;
import com.iven.ivenrpc.server.HttpServer;
import com.iven.ivenrpc.server.VertexHttpServer;

public class EasyProviderExample {
    public static void main(String[] args) {
        //Rpc框架初始化
        RpcApplication.init();
        //注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);
        //启动web服务
        HttpServer httpServer = new VertexHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());




//        LocalRegistry registry = new LocalRegistry();
//        registry.register(UserService.class.getName(),UserServiceImpl.class);
//        //启动web服务
//        HttpServer httpServer = new VertexHttpServer();
//        httpServer.doStart(8080);
    }
}
