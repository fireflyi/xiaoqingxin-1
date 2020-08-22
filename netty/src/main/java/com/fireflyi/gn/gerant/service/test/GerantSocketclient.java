package com.fireflyi.gn.gerant.service.test;

import com.fireflyi.gn.gerant.common.protobuf.GerantReqProtobuf;
import com.fireflyi.gn.gerant.service.SimpleClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
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
public class GerantSocketclient {

    private static final Integer PORT = 6388;

    public static void main(String[] args){

        // 首先，netty通过ServerBootstrap启动服务端
        Bootstrap client = new Bootstrap();

        EventLoopGroup group = new NioEventLoopGroup();
        try{
            client.group(group);
            client.channel(NioSocketChannel.class);
            client.handler(new ChannelInitializer<Channel>() {  //通道是NioSocketChannel
                @Override
                protected void initChannel(Channel ch) throws Exception {
                    //字符串编码器，一定要加在SimpleClientHandler 的上面
                    ch.pipeline()
                    .addLast("decoder", new StringDecoder())
                    .addLast("encoder", new StringEncoder())
                    .addLast(new SimpleClientHandler());
                }
            });

            // 发起异步连接服务器
            ChannelFuture ch = client.connect("127.0.0.1", PORT);
            //发送数据
            ChannelFuture future=null;
            for(int i=0;i<10;i++){
            ByteBuf buf = Unpooled.copiedBuffer("hello server".getBytes());


                future = ch.channel().writeAndFlush(buf);
                future.addListener((ChannelFutureListener) channelFuture ->
                        System.out.println("客户端手动发消息成功"));
            }
            //当通道关闭了，就继续往下走
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //优雅的退出程序
            group.shutdownGracefully();
        }
    }

}
