package com.iven.ivenrpc.server;

import io.vertx.core.Vertx;

public class VertexHttpServer implements HttpServer{
    @Override
    public void doStart(int port) {
        //创建Vertx.x实例
        Vertx vertx = Vertx.vertx();

        //创建http服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        //监听端口并处理请求
        server.requestHandler(new HttpServerHandler());

        server.listen(port,result ->{
            if(result.succeeded()){
                System.out.println("Server started on port "+port);
            }else{
                System.out.println("failed to start server"+result.cause());
            }
        });
    }
}
