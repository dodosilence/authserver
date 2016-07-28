package cc.moondust.authserver.util;

import com.sun.istack.internal.NotNull;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.io.File;

/**
 * Created by j0 on 2016/7/28.
 */
public class ResponseParser {

    public static void parser(){


    }

    public static void sendStaticPage(@NotNull ChannelHandlerContext ctx,@NotNull String pageName){
        File html404 = new File(ResponseParser.class.getClassLoader().getResource("views/error/"+pageName+".html").getFile());
        String html = HtmlFileReader.readTxtFile(html404);
        ByteBuf byteBuf = Unpooled.copiedBuffer(html.getBytes());
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
        fullHttpResponse.headers().add("content-type", "text/html;charset=utf-8");
        ctx.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
    }
}
