package com.iven.ivenrpc.server.tcp;

import io.vertx.core.Vertx;

public class VertxTcpClient {
    public void start(){
        //创建vertx实例
        Vertx vertx = Vertx.vertx();

        vertx.createNetClient().connect(8888,"localhost",result->{
            if(result.succeeded()){
                System.out.println("Connected to vert.x");
                io.vertx.core.net.NetSocket socket = result.result();
                //发送数据
                socket.write("Hello World");
                //接收响应
                socket.handler(buffer->{
                    System.out.println("Client received: " + buffer.toString());
                });
            }else{
                System.err.println("Failed to connect to vert.x");
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpClient().start();
    }
}
