package com.iven.ivenrpc.serializer;

import com.iven.ivenrpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

//序列化器工厂
public class SerializerFactory {
    //序列化器映射（用于实现单例）
//    private static final Map<String,Serializer> KEY_SERIALIZER_MAP = new HashMap<String,Serializer>(){{
//        put(SerializerKeys.JDK, new JdkSerializer());
//        put(SerializerKeys.JSON, new JsonSerializer());
//    }};
    static {
        SpiLoader.load(Serializer.class);
    }

    //默认序列化器
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    //获取实例

    public static Serializer getInstance(String key){
        return SpiLoader.getInstance(Serializer.class, key);
    }
}
