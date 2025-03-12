package com.iven.ivenrpc.server.tcp;

import com.iven.ivenrpc.server.HttpServer;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

public class VertxTcpServer implements HttpServer {
//    private byte[] handleRequest(byte[] requestData) {
//        return "Hello World!".getBytes();
//    }
    @Override
    public void doStart(int port) {
        //创建vertx实例
        Vertx vertx = Vertx.vertx();

        //创建TCP服务器
        NetServer server = vertx.createNetServer();

        //处理请求
        server.connectHandler(new TcpServerHandler());
//        server.connectHandler(socket -> {
//            //处理连接
//            socket.handler(buffer -> {
//                //处理收到的字节数组
//                byte[] requestData = buffer.getBytes();
//                //在这里进行自定义的字节数组处理逻辑，比如解析请求，调用服务，构造响应等
//                byte[] responseData = handleRequest(requestData);
//                //发送响应
//                socket.write(Buffer.buffer(responseData));
//            });
//        });


        //启动TCP服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server started on port " + port);
            }else{
                System.out.println("Failed to start server"+result.cause());
            }
        });
    }

//    public static void main(String[] args) {
//        new VertxTcpServer().doStart(8888);
//    }
}
