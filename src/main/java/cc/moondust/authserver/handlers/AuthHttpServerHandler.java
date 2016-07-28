package cc.moondust.authserver.handlers;

import cc.moondust.authserver.controller.AuthorizeController;
import cc.moondust.authserver.util.RequestParser;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Tristan on 16/7/17.
 */
@Component
@ChannelHandler.Sharable
public class AuthHttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private static final Logger logger = LoggerFactory.getLogger(AuthHttpServerHandler.class.getName());


    @Value("#{cnf.authserver_path}")
    private String websocketPath;

    @Autowired
    DispacherHandler dispacherHandler;




    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) {
        if (HttpMethod.POST.equals(fullHttpRequest.method())) {//处理post请求
            try {
                dispacherHandler.dispacher(fullHttpRequest, channelHandlerContext);
            } catch (Exception e) {
                sendMethodNotSupportHttpResponse(channelHandlerContext, fullHttpRequest.method().toString());
            }
        } else {
            sendMethodNotSupportHttpResponse(channelHandlerContext, fullHttpRequest.method().toString());
        }
    }


    private void sendMethodNotSupportHttpResponse(ChannelHandlerContext channelHandlerContext, String method) {
        StringBuffer res = new StringBuffer("<!DOCTYPE html>\n")
                .append("<html lang=\"zh\">\n")
                .append("<head>\n")
                .append("    <meta charset=\"UTF-8\">\n")
                .append("    <link rel=\"shortcut icon\" href=\"http://localhost:8080/favicon.ico\" />\n")
                .append("    <title>授权响应</title>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<h4 style=\"text-align: center;padding-top: 200px;color: #5ebc0a\"><b>对不起，系统不支持")
                .append(method)
                .append("请求</b></h4></body></html>");

        ByteBuf byteBuf = Unpooled.copiedBuffer(res.toString().getBytes());
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
        fullHttpResponse.headers().add("content-type", "text/html;charset=utf-8");
        channelHandlerContext.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
        byteBuf.retain();
    }


}
