package com;

import com.core.WebSocketHandler;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author by lishihao
 * @date 2019/10/16
 * DESC TODO
 */

public class SocketInitializer  extends ChannelInitializer<SocketChannel> {

    @Inject
    private WebSocketHandler webSocketHandler;

    @Override
    protected void initChannel(SocketChannel e) throws Exception {
        e.pipeline().addLast(new IdleStateHandler(180, 0, 0, TimeUnit.SECONDS));
        e.pipeline().addLast("http-codec",new HttpServerCodec());
        e.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
        e.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        e.pipeline().addLast("handler", webSocketHandler);
    }

}
