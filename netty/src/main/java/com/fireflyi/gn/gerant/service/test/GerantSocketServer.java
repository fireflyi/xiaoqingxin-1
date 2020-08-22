package com.fireflyi.gn.gerant.service.test;

import com.fireflyi.gn.gerant.common.protobuf.GerantReqProtobuf;
import com.fireflyi.gn.gerant.service.handle.GerantServerHandle;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author by fireflyi (6025606@qq.com)
 * @website https://www.fireflyi.com
 * @date 2019/7/20
 * DESC TODO
 */
public class GerantSocketServer {

    private static final Integer PORT = 6388;

    public static void main(String[] args){
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup, workGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer<Channel>() {  //通道是NioSocketChannel
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    //字符串编码器，一定要加在GerantServerHandle 的上面
                    ch.pipeline()
                            .addLast("decoder", new StringDecoder())
                            .addLast("encoder", new StringEncoder())
                            .addLast(new GerantServerHandle());
                }
            });
            System.out.println("服务端等待客户端连接...");
            // 绑定端口，同步等待成功
            Channel ch = b.bind(PORT).sync().channel();
            // 等待服务端监听端口关闭
            ch.closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //优雅的退出程序
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
