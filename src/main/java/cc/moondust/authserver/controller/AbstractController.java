package cc.moondust.authserver.controller;

import cc.moondust.authserver.controller.model.PageModel;
import cc.moondust.authserver.exception.ParamsNotLegalException;
import cc.moondust.authserver.util.RequestParser;
import cc.moondust.authserver.util.ResponseParser;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.util.Map;

/**
 * Created by j0 on 2016/7/28.
 */
public abstract class AbstractController {

    public void doService(FullHttpRequest request, ChannelHandlerContext channelHandlerContext) {
        Map<String, String> params = RequestParser.parse(request);
        String uri = request.uri();
        HttpHeaders headers = request.headers();
        PageModel model = this.service(uri, params, request.method(),headers);
        parseModel(channelHandlerContext,model);
    }

    protected abstract PageModel service(String uri, Map<String, String> params, HttpMethod method, HttpHeaders headers) throws ParamsNotLegalException;


    public void parseModel(ChannelHandlerContext ctx, PageModel model) {
        if (model==null){
            ResponseParser.sendStaticPage(ctx,"404");
        }
    }
}
