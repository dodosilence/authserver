package cc.moondust.authserver.netty;

import cc.moondust.authserver.handlers.AuthHttpServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Tristan on 16/7/17.
 */
@Component
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Autowired
    private AuthHttpServerHandler authHttpServerHandler;


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

//        if (true) {
//            SSLEngine engine = SecureChatSslContextFactory.getServerContext().createSSLEngine();
//            engine.setUseClientMode(false);
//            pipeline.addLast("ssl", new SslHandler(engine));
//        }
        pipeline.addLast("http-decoder",
                new HttpRequestDecoder());
        // 聚合器，把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse
        pipeline.addLast("http-aggregator",
                new HttpObjectAggregator(65536));
        // 服务端，对响应编码
        pipeline.addLast("http-encoder",
                new HttpResponseEncoder());
        // 块写入处理器
        pipeline.addLast("http-chunked",
                new ChunkedWriteHandler());
        // 自定义服务端处理器
        pipeline.addLast("fileServerHandler",
                authHttpServerHandler);
    }
}
