package com.iven.example.consumer;

import com.iven.example.common.model.User;
import com.iven.example.common.service.UserService;
import com.iven.ivenrpc.proxy.ServiceProxyFactory;

public class EasyConsumerExample {
    public static void main(String[] args) {
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
