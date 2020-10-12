package com.hqq.NettyClient.nettyClient;

import com.hqq.NettyClient.model.TransferPackageWrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by huqiaoqian on 2020/10/12
 */
public class StrMessageToByteEncoder extends MessageToByteEncoder<TransferPackageWrap> {
    @Override
    protected void encode(ChannelHandlerContext ctx, TransferPackageWrap msg, ByteBuf out) throws Exception {
        //这里发送数据顺序必须和数据接收端（服务端）的接收顺序保持一致（TCP保证数据顺序）
        //发送数据包长度
        out.writeInt(msg.getLength());
        //发送数据包
        out.writeBytes(msg.getBytes());
    }
}
