package com.iven.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.iven.example.common.model.User;
import com.iven.example.common.service.UserService;
import com.iven.ivenrpc.model.RpcRequest;
import com.iven.ivenrpc.model.RpcResponse;
import com.iven.ivenrpc.serializer.JdkSerializer;
import com.iven.ivenrpc.serializer.Serializer;



import java.io.IOException;

//静态代理
public class UserServiceProxy implements UserService {
    public User getUser(User user) {
        //指定序列化器
        Serializer serializer = new JdkSerializer();

        //发送请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        try{
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            try(HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()){
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        }catch(IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
