package cc.moondust.authserver.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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




    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        RequestParser requestParser = new RequestParser(fullHttpRequest);
        Map<String, String> parse = requestParser.parse();

        ByteBuf byteBuf = Unpooled.copiedBuffer("hello 中国".getBytes());

        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,byteBuf);
        fullHttpResponse.headers().add("content-type","text/plan;charset=utf-8");
        channelHandlerContext.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
    }
}
