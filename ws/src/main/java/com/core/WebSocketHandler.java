package com.core;

import com.utils.WsUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author by lishihao
 * @date 2019/10/17
 * DESC TODO
 */
@ChannelHandler.Sharable
public class WebSocketHandler  extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    private static final String WEB_SOCKET_URL = "ws://127.0.0.1:8666/ws";

    @Override
    protected void channelRead0(ChannelHandlerContext context, Object msg) throws Exception {
        System.out.println("有新消息");
        //处理客户端向服务端发起http握手请求业务
        if (msg instanceof FullHttpRequest) {
            handHttpRequest(context, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {//处理websocket连接业务
            handWebsocketFrame(context, (WebSocketFrame) msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有新链接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("链接断开");
        ctx.close() ;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //异常时断开连接
        System.out.println("异常链接断开");
        cause.printStackTrace() ;
        ctx.close() ;
    }

    /**
     * 服务端处理客户端ws请求的核心方法
     *
     * @param context
     * @param msg
     * @throws Exception
     */
    protected void messageReceived(ChannelHandlerContext context, Object msg) throws Exception {

    }


    /**
     * 处理客户端与服务端websocket业务
     *
     * @param ctx
     * @param frame
     */
    public void handWebsocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) throws UnsupportedEncodingException {
        String fromChannelid = ctx.channel().id().toString();

        //判断是否是关闭websocket的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
        }

        //判断是否是ping消息
        if (frame instanceof PingWebSocketFrame) {
            System.out.println("ping消息");
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
        }



        if(frame instanceof BinaryWebSocketFrame){
            System.out.println("收到二进制数据");
            a(ctx, frame);
            //b(ctx, frame);
            return;
        }

        //返回应答消息
        //获取客户端向服务端发送的消息
        String request = (((TextWebSocketFrame) frame).text()).replaceAll("[\\s*\\t\\n\\r]", "");
        System.out.println("客户端消息"+request);
    }

//    public void b(ChannelHandlerContext ctx, WebSocketFrame frame){
//        BinaryWebSocketFrame msg = (BinaryWebSocketFrame) frame;
//        ByteBuf content = msg.content();
//        content.markReaderIndex();
//        int flag = content.readInt();
//        System.out.printf(flag+"");
//        content.resetReaderIndex();
//
//        ByteBuf byteBuf = Unpooled.directBuffer(msg.content().capacity());
//        byteBuf.writeBytes(msg.content());
//
//        ctx.writeAndFlush(new BinaryWebSocketFrame(byteBuf));
//    }

    public void a(ChannelHandlerContext ctx, WebSocketFrame frame) throws UnsupportedEncodingException {
        BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) frame;
        ByteBuf content = binaryWebSocketFrame.content();

        //ByteBuf转String
        byte[] req = new byte[content.readableBytes()];
        content.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.println("二进制数据->"+content+",转str->"+body);
        WsUtils.flushFileByte2(body,"./ss.jpeg");

        //String转ByteBuf
        String msg = "服务端收到信息->"+body;
        byte[] bytes = msg.getBytes(CharsetUtil.UTF_8);
        ByteBuf buf = Unpooled.wrappedBuffer(bytes);
        ctx.writeAndFlush(new BinaryWebSocketFrame(buf));
    }

    /**
     * 处理客户端向服务端发起http握手请求的业务
     * @param ctx
     * @param req
     */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        if (!req.getDecoderResult().isSuccess() || !("websocket".equals(req.headers().get("Upgrade")))) {
            //处理不是握手请求
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(WEB_SOCKET_URL, null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }

    }

    /**
     * 服务端向客户端响应消息
     *
     * @param ctx
     * @param req
     * @param res
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        //服务端向客户端发送数据
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);//关闭连接
        }
    }

}
