package cc.moondust.authserver.handlers;

import cc.moondust.authserver.controller.AccessTokenController;
import cc.moondust.authserver.controller.AuthorizeController;
import cc.moondust.authserver.exception.ResourceNotFoundException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by j0 on 2016/7/28.
 */
@Component
public class DispacherHandler {
    @Autowired
    AuthorizeController authorizeController;

    @Autowired
    AccessTokenController accessTokenController;




    public void dispacher(FullHttpRequest fullHttpRequest, ChannelHandlerContext channelHandlerContext) throws ResourceNotFoundException {
        if (fullHttpRequest.uri().startsWith("/auth")) {
            authorizeController.doService(fullHttpRequest,channelHandlerContext);
        } else if (fullHttpRequest.uri().startsWith("/token")) {
            accessTokenController.doService(fullHttpRequest,channelHandlerContext);
        }else {
            throw  new ResourceNotFoundException("404");
        }

    }
}
