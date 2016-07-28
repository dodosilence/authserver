package cc.moondust.authserver.controller;

import cc.moondust.authserver.controller.model.PageModel;
import cc.moondust.authserver.util.HtmlFileReader;
import cc.moondust.authserver.util.RequestParser;
import cc.moondust.authserver.util.ResponseParser;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.File;
import java.util.Map;

/**
 * Created by j0 on 2016/7/28.
 */
public abstract class AbstractController {

    public void doService(FullHttpRequest request, ChannelHandlerContext channelHandlerContext) {
        Map<String, String> params = RequestParser.parse(request);
        String uri = request.uri();
        PageModel model = this.service(uri, params, request.method());
        parseModel(channelHandlerContext,model);
    }

    protected abstract PageModel service(String uri, Map<String, String> params, HttpMethod method);


    public void parseModel(ChannelHandlerContext ctx, PageModel model) {
        if (model==null){
            ResponseParser.sendStaticPage(ctx,"404");
        }
    }
}
