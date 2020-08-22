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

/**
 * @author by lishihao
 * @date 2019/10/17
 * DESC TODO
 */
@ChannelHandler.Sharable
public class WebSocketHandler  extends SimpleChannelInboundHandler<Object> {

    private int MAGIC_BIN_NUM = 0x654321;

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
            binaryWebSocketFrame(ctx, frame);
            return;
        }

        //返回应答消息
        //获取客户端向服务端发送的消息
        String request = (((TextWebSocketFrame) frame).text()).replaceAll("[\\s*\\t\\n\\r]", "");
        System.out.println("客户端消息"+request);
    }


    public void binaryWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame){
        BinaryWebSocketFrame msg = (BinaryWebSocketFrame) frame;
        ByteBuf content = msg.content();
        byte[] byteWS = new byte[content.readableBytes()];
        content.readBytes(byteWS);//ByteBuf转字节数组
        //协议头校验
        if(!protocol(content)){ return; }
        //协议文件扩展类型
        byte[] extByte = subBytes(byteWS, 4, 1);
        String ext = byteToExt(extByte);
        byte[] imgByte = subBytes(byteWS, 5, byteWS.length-5);
        //字节流写文件
        WsUtils.writeFile(imgByte, ext);
    }

    /**
     * 协议头
     * @param content
     * @return
     */
    private boolean protocol(ByteBuf content){
        int protocolCode = content.getInt(0);
        System.out.println("protocolCode->"+protocolCode);
        if(protocolCode == MAGIC_BIN_NUM){
            return true;
        }
        return false;
    }

    /**
     * 解析协议头，文件扩展类型
     * @param bytes
     * @return
     */
    public String byteToExt(byte[] bytes) {
        return WsUtils.extCodeToType(bytes[0]);
    }

    /**
     * 截取字节数组
     * @param src
     * @param begin
     * @param count
     * @return
     */
    public byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.out.println(src.length+"src");
        for (int i=begin;i<begin+count; i++) bs[i-begin] = src[i];
        return bs;
    }

    /**
     * 解析协议
     * @param bytes
     * @return
     */
    public int byteArrayToInt(byte[] bytes) {
        int value=0;
        for(int i = 0; i < 4; i++) {
            int shift= (4-1-i) * 8;
            value +=(bytes[i] & 0x000000FF) << shift;//往高位游
        }
        return value;
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
