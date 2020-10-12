package com.hqq.NettyClient.nettyClient;

import com.hqq.NettyClient.model.TransferPackageWrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.unix.Socket;
import io.netty.util.CharsetUtil;
import org.apache.commons.codec.binary.Hex;

import java.net.InetSocketAddress;

/**
 * Created by huqiaoqian on 2020/10/12
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    private Channel channel;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        channel = ctx.channel();
        byte[] data = {(byte)0x68,(byte)0x86,(byte)0x77,(byte)0x26,
                (byte)0x03,(byte)0x17,(byte)0x67,(byte)0x32,(byte)0x20,
                (byte)0x81,(byte)0x05,(byte)0x20,(byte)0x02,(byte)0x01,(byte)0x00};
        TransferPackageWrap transferPackageWrap=new TransferPackageWrap();
        transferPackageWrap.setBytes(data);
        transferPackageWrap.setLength(data.length);
        System.out.println(transferPackageWrap);
        sendMessage(transferPackageWrap);
    }
    public void sendMessage(TransferPackageWrap transferPackage) {
        if (channel != null && channel.isWritable()) {
            channel.writeAndFlush(transferPackage);
        }
    }
}
