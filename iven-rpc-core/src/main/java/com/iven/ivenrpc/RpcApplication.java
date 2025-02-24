package com.iven.ivenrpc;

import com.iven.ivenrpc.config.RpcConfig;
import com.iven.ivenrpc.constant.RpcConstant;
import com.iven.ivenrpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;


//@Slf4j
public class RpcApplication {
    private static volatile RpcConfig rpcConfig;

    //框架初始化，支持传入自定义配置
    public static void init(RpcConfig newRpcConfig){
        rpcConfig = newRpcConfig;
//        log.info("rpc,init:{}",newRpcConfig.toString());
    }

    //初始化
    public static void init(){
        RpcConfig newRpcConfig;
        try{
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        }catch (Exception e){
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    //获取配置
    public static RpcConfig getRpcConfig(){
        if(rpcConfig == null){
            synchronized (RpcApplication.class){
                if(rpcConfig == null){
                    init();
                }
            }
        }
        return rpcConfig;
    }

}
