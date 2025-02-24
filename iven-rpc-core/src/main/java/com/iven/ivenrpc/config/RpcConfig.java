package com.iven.ivenrpc.config;

import lombok.Data;

//rpc配置框架
@Data
public class RpcConfig {
    //名称
    private String name = "iven-rpc";

    //版本号
    private String version ="1.0";

    //服务器主机名
    private String serverHost = "localhost";

    //服务器端口号
    private Integer serverPort = 8080;
}
