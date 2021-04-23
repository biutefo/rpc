package com.xin.rpc.common.codec.protostuff;

import com.xin.rpc.common.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Description : protostuff 序列化
 * @Author : xin
 * @Created : 2021-04-23 11:29 上午
 */
public class ProtostuffEncoder<I> extends MessageToByteEncoder<I> {
    private final Class<I> genericClass;

    public ProtostuffEncoder(Class<I> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, I msg, ByteBuf out) throws Exception {
        if (genericClass.isInstance(msg)) {
            byte[] data = SerializationUtil.serialize(msg);
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
