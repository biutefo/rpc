package com.xin.rpc.common.codec.json;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Description : json 反序列化
 * @Author : xin
 * @Created : 2021-04-23 11:52 上午
 */
@SuppressWarnings("unused")
public class JsonDecoder<T> extends ByteToMessageDecoder {
    private final Class<T> genericClass;

    public JsonDecoder(Class<T> genericClass) {
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
        T t = JSON.parseObject(new String(data, StandardCharsets.UTF_8), genericClass);
        out.add(t);
    }
}
