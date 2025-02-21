package com.iven.example.provider;

import com.iven.ivenrpc.server.HttpServer;
import com.iven.ivenrpc.server.VertexHttpServer;

public class EasyProviderExample {
    public static void main(String[] args) {
        //启动web服务
        HttpServer httpServer = new VertexHttpServer();
        httpServer.doStart(8080);
    }
}
