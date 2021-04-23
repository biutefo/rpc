package com.xin.rpc.common.codec.protostuff;

import com.xin.rpc.common.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Description : protostuff 反序列化
 * @Author : xin
 * @Created : 2021-04-23 11:38 上午
 */
public class ProtostuffDecoder<T> extends ByteToMessageDecoder {
    private final Class<T> genericClass;

    public ProtostuffDecoder(Class<T> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        T t = SerializationUtil.deserialize(data, genericClass);
        out.add(t);
    }
}
