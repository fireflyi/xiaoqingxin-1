package com;

import com.google.inject.Inject;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author by lishihao
 * @date 2019/10/16
 * DESC TODO
 */
public class SocketApplication {

    @Inject
    SocketInitializer socketInitializer;

    @Inject
    public void run(){

        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup, workGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(socketInitializer);

            Channel ch = b.bind(1234).sync().channel();

            //注册zk
//            Thread t = new Thread(serverRegistryZK);
//            t.start();

            //
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
