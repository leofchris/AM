/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AM.Handlers;

import AM.Server.serverHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import io.netty.handler.codec.http.HttpUtil;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import io.netty.util.CharsetUtil;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

/**
 *
 * @author Chris
 */
public class BGHandler implements serverHandler{

    @Override
    public void handlePacket(ChannelHandlerContext ctx, Object msg) {
        try{
    
       byte[] data = Files.readAllBytes(Paths.get("C:\\Users\\Chris\\Desktop\\AM\\html\\BG.jpg"));
       
       FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, ((HttpObject)msg).decoderResult().isSuccess()? OK : BAD_REQUEST, Unpooled.copiedBuffer(data));
        
        
       HttpRequest request = (HttpRequest)msg; 
       boolean keepAlive = HttpUtil.isKeepAlive(request);
       response.headers().set(HttpHeaderNames.CONTENT_TYPE, "image/jpeg");

        if (keepAlive) {
            // Add 'Content-Length' header only for a keep-alive connection.
            response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            // Add keep alive header as per:
            // - http://www.w3.org/Protocols/HTTP/1.1/draft-ietf-http-v11-spec-01.html#Connection
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

       
        // Write the response.
        ctx.write(response);
    }
    catch(Exception e){
    
    }
    }
    
}
