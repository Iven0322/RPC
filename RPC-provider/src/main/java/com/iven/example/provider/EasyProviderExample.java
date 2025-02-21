package com.iven.example.provider;

import com.iven.example.common.service.UserService;
import com.iven.ivenrpc.registry.LocalRegistry;
import com.iven.ivenrpc.server.HttpServer;
import com.iven.ivenrpc.server.VertexHttpServer;

public class EasyProviderExample {
    public static void main(String[] args) {
        LocalRegistry registry = new LocalRegistry();
        registry.register(UserService.class.getName(),UserServiceImpl.class);
        //启动web服务
        HttpServer httpServer = new VertexHttpServer();
        httpServer.doStart(8080);
    }
}
