package com.iven.example.consumer;

import com.iven.example.common.model.User;
import com.iven.example.common.service.UserService;
import com.iven.ivenrpc.config.RpcConfig;
import com.iven.ivenrpc.proxy.ServiceProxyFactory;
import com.iven.ivenrpc.utils.ConfigUtils;

public class EasyConsumerExample {
    public static void main(String[] args) {
        //加载配置信息
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class,"rpc");
        System.out.println(rpc);

        //静态代理
//        UserService userService = new UserServiceProxy();
        //动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);

        User user = new User();

        user.setName("iven");

        User newUser = userService.getUser(user);

        if(newUser != null) {
            System.out.println(newUser.getName());
        }else{
            System.out.println("user == null");
        }
    }
}
