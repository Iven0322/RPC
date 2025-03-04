package com.iven.ivenrpc;

import com.iven.ivenrpc.config.RegistryConfig;
import com.iven.ivenrpc.config.RpcConfig;
import com.iven.ivenrpc.constant.RpcConstant;
import com.iven.ivenrpc.registry.Registry;
import com.iven.ivenrpc.registry.RegistryFactory;
import com.iven.ivenrpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class RpcApplication {
    private static volatile RpcConfig rpcConfig;

    //框架初始化，支持传入自定义配置
    /**
     * 框架初始化，支持传入自定义配置
     *
     * @param newRpcConfig
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
        // 注册中心初始化
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("registry init, config = {}", registryConfig);

        //创建并注册shutdown hook，JVM退出时执行这个操作
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));
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
