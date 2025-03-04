package com.iven.ivenrpc.server;

import com.iven.ivenrpc.RpcApplication;
import com.iven.ivenrpc.model.RpcRequest;
import com.iven.ivenrpc.model.RpcResponse;
import com.iven.ivenrpc.registry.LocalRegistry;
import com.iven.ivenrpc.serializer.JdkSerializer;
import com.iven.ivenrpc.serializer.Serializer;
import com.iven.ivenrpc.serializer.SerializerFactory;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.HttpServerRequest;


import java.io.IOException;
import java.lang.reflect.Method;

public class HttpServerHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest request) {
        //指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());
        //记录日志
        System.out.println("Received request: " + request.method()+" "+ request.uri());

        // 异步处理 HTTP 请求
        request.bodyHandler(body -> {
            //获取比特流
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                //将比特流反序列化为RpcRequest对象
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //构造响应结果对象
             RpcResponse rpcResponse =new RpcResponse();
            //如果请求为null，直接返回
            if(rpcRequest == null){
                rpcResponse.setMessage("Request is null!");
                doResponse(request,rpcResponse,serializer);
                return;
            }

            try {
                //先在rpcRequest对象中找到需要调用的服务名字，再到LocalRegistry注册中心寻找服务名字对应的服务实现类
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                //通过反射调用服务类的方法
                //使用Method类中的getMethod方法，找到implClass服务实现类中需要使用的方法名和参数类型列表
                Method method = implClass.getMethod(rpcRequest.getMethodName(),rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(),rpcRequest.getArgs());
                //封装返回结果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("OK");
            }catch (Exception e){
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            //响应
            doResponse(request,rpcResponse,serializer);
        });
    }

    void doResponse(HttpServerRequest request,RpcResponse rpcResponse,Serializer serializer){
        //使用reponse函数得到一个http响应对象，填充头部，设置格式为json等
        HttpServerResponse httpServerResponse = request.response()
                .putHeader("content-type","application/json; charset=utf-8");
        try{
            //将rpcresponse响应对象序列化
            byte[] serialized = serializer.serialize(rpcResponse);
            //将序列化的字节数组转化为buffer对象放入http响应对象中
            httpServerResponse.end(Buffer.buffer(serialized));
        }catch(IOException e){
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }

    }
}


