package com.iven.ivenrpc.protocol;

import com.iven.ivenrpc.model.RpcRequest;
import com.iven.ivenrpc.model.RpcResponse;
import com.iven.ivenrpc.serializer.Serializer;
import com.iven.ivenrpc.serializer.SerializerFactory;
import io.vertx.core.buffer.Buffer;

import java.io.IOException;

//协议消息解码器
public class ProtocolMessageDecoder {
    //解码
    public static ProtocolMessage<?> decode(Buffer buffer) throws IOException {
        //分别从指定位置读出buffer
        ProtocolMessage.Header header = new ProtocolMessage.Header();
        byte magic = buffer.getByte(0);
        //校验魔数
        if(magic != ProtocolConstant.PROTOCOL_MAGIC){
            throw new RuntimeException("Invalid protocol magic");
        }
        header.setMagic(magic);
        header.setVersion(buffer.getByte(1));
        header.setSerializer(buffer.getByte(2));
        header.setType(buffer.getByte(3));
        header.setStatus(buffer.getByte(4));
        header.setRequestId(buffer.getLong(5));
        header.setBodyLength(buffer.getInt(13));
        //解决粘包问题，只读指定大小的数据
        byte[] bodyBytes = buffer.getBytes(17,17+header.getBodyLength());
        //解析消息体
        ProtocolMessageSerializerEnum serializerEnum = ProtocolMessageSerializerEnum.getEnumByKey(header.getSerializer());
        if(serializerEnum == null){
            throw new RuntimeException("Unknown serializer");
        }
        Serializer serializer = SerializerFactory.getInstance(serializerEnum.getValue());
        ProtocolMessageTypeEnum  messageTypeEnum = ProtocolMessageTypeEnum.getEnumByKey(header.getType());
        if(messageTypeEnum == null){
            throw new RuntimeException("Unknown message type");
        }
        switch (messageTypeEnum){
            case REQUEST:
                RpcRequest rpcRequest = serializer.deserialize(bodyBytes, RpcRequest.class);
                return new ProtocolMessage<>(header, rpcRequest);
            case RESPONSE:
                RpcResponse rpcResponse = serializer.deserialize(bodyBytes, RpcResponse.class);
                return new ProtocolMessage<>(header, rpcResponse);
            case HEART_BEAT:
            case OTHERS:
            default:
                throw new RuntimeException("Unknown message type");
        }
    }
}
