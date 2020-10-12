package com.hqq.NettyClient.nettyClient;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    private String host;
    private Integer port;
    private Bootstrap bootstrap;
    public NettyClient(String host, Integer port) {
        this.port=port;
        this.host = host;
        this.bootstrap = setup();
    }
    private Bootstrap setup() {
        EventLoopGroup group = new NioEventLoopGroup();
        return new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StrMessageToByteEncoder());
                        pipeline.addLast(new NettyClientHandler());
                    }
                });
    }

    public void run() throws InterruptedException {
        bootstrap.connect(host, port).sync()
                .channel().closeFuture().sync();
    }
}
