package com.iven.ivenrpc.serializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iven.ivenrpc.model.RpcRequest;
import com.iven.ivenrpc.model.RpcResponse;

import java.io.IOException;

public class JsonSerializer implements Serializer {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Override
    public byte[] serialize(Object obj) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) throws IOException {
        T obj = OBJECT_MAPPER.readValue(data,clazz);
        if(obj instanceof RpcRequest){
            return handleRequest((RpcRequest)obj,clazz);
        }
        if(obj instanceof RpcResponse){
            return handleResponse((RpcResponse)obj,clazz);
        }
        return obj;
    }

    //由于object的原始对象会被擦除，导致反序列化时会被作为LinkedHashMap，无法转换成原始对象，这里做特殊处理
    private <T> T handleRequest(RpcRequest request, Class<T> clazz) throws IOException {
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] args = request.getArgs();

        //循环处理每个参数的类型
        for(int i = 0; i < parameterTypes.length; i++){
            Class<?> parameterType = parameterTypes[i];
            //如果类型不同，则重新处理一下类型
            if(!parameterType.isAssignableFrom(args[i].getClass())){
                byte[] argBytes = OBJECT_MAPPER.writeValueAsBytes(args[i]);
                args[i] = OBJECT_MAPPER.readValue(argBytes,parameterType);
            }
        }
        return clazz.cast(request);
    }

    private <T> T handleResponse(RpcResponse rpcResponse, Class<T> type) throws IOException {
        //处理响应数据
        byte[] dataBytes = OBJECT_MAPPER.writeValueAsBytes(rpcResponse.getData());
        rpcResponse.setData(OBJECT_MAPPER.readValue(dataBytes,rpcResponse.getDataType()));
        return type.cast(rpcResponse);
    }
}
