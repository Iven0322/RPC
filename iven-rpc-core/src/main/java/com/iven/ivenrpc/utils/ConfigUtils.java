package com.iven.ivenrpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

//配置工具类
public class ConfigUtils {
    //加载配置对象
    public static <T> T loadConfig(Class<T> tClass,String prefix){
        return loadConfig(tClass,prefix,"");
    }
    //加载配置对象,支持区分环境
    public static <T> T loadConfig(Class<T> tClass,String prefix,String enviroment){
        StringBuilder configFileBuilder = new StringBuilder("application");
        if(StrUtil.isNotBlank(enviroment)){
            configFileBuilder.append("-").append(enviroment);
        }

        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass,prefix);
    }
}
