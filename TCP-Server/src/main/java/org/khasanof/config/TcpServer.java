package org.khasanof.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/4/26
 */
@Component
@RequiredArgsConstructor
public class TcpServer {

    @Value("${tcp.port}")
    private int port;

    private final ChannelInitializer<SocketChannel> initializer;

    public void start() throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(initializer);

        ChannelFuture future = bootstrap.bind(port).sync();
        System.out.println("TCP Server started on port: " + port);

        future.channel().closeFuture().sync();
    }
}
