package org.khasanof.config;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 5/4/26
 */
@Component
@RequiredArgsConstructor
public class TcpChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final TcpHandler handler;

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();

        // Incoming: bytes → string
        pipeline.addLast(new StringDecoder(StandardCharsets.UTF_8));

        // Outgoing: string → bytes
        pipeline.addLast(new StringEncoder(StandardCharsets.UTF_8));

        pipeline.addLast(handler);
    }
}
