package com.xin.rpc.common.codec.json;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @Description : json 序列化
 * @Author : xin
 * @Created : 2021-04-23 11:43 上午
 */
@SuppressWarnings("unused")
public class JsonEncoder<I> extends MessageToByteEncoder<I> {
    @Override
    protected void encode(ChannelHandlerContext ctx, I msg, ByteBuf out) throws Exception {
        byte[] data = JSON.toJSONString(msg).getBytes(StandardCharsets.UTF_8);
        out.writeInt(data.length);
        out.writeBytes(data);
    }
}
