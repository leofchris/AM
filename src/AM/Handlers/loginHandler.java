/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AM.Handlers;
import AM.Server.serverHandler;
import AM.database.database;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import static io.netty.channel.ChannelFutureListener.CLOSE;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import static io.netty.handler.codec.http.HttpHeaderNames.KEEP_ALIVE;
import io.netty.handler.codec.http.HttpHeaderValues;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.QueryStringDecoder;

import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import static io.netty.handler.codec.http.HttpVersion.*;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MixedAttribute;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Chris
 */
public class loginHandler implements serverHandler{

    @Override
    public void handlePacket(ChannelHandlerContext ctx, Object msg) {
        
        String login = "dasdsaas";
        String password = "dasdasdasd";
        int bypassAttempt = 0;
        
        String sqlLogin = null;
        String sqlPassword = null;
        if (((HttpRequest)msg).getMethod().equals(HttpMethod.POST)){
            
           HttpPostRequestDecoder decoder = new HttpPostRequestDecoder((HttpRequest) msg);
        
        try{
            
            List<InterfaceHttpData> postList = decoder.getBodyHttpDatas();
            
            for(InterfaceHttpData data : postList){
              
                MixedAttribute value = (MixedAttribute)data;
                if (value.getName().equalsIgnoreCase("email")){
                    login = value.getValue();
                } if (value.getName().equalsIgnoreCase("password")){
                    password = value.getValue();
                }
              }
              decoder.destroy();
            }
            catch(Exception e){
                
            } 
        }else{
            bypassAttempt = 1;
        }
       
    try{
      Connection con = database.connectToDB();
      PreparedStatement ps = con.prepareStatement("SELECT * FROM login;");
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
          sqlLogin = rs.getString("login");
          sqlPassword = rs.getString("password");
      }
       rs.close();
       ps.close();
       con.close();
    } catch(SQLException e){
      System.out.println(e);
    } 
    
    //Compare logic
    if (login.equalsIgnoreCase(sqlLogin) && password.equalsIgnoreCase(sqlPassword) && bypassAttempt == 0){
       
        try{
            
        HttpRequest request = (HttpRequest)msg; 
        boolean keepAlive = HttpUtil.isKeepAlive(request);
        byte[] data = (Files.readAllBytes(Paths.get("C:\\Users\\Chris\\Desktop\\AM\\html\\login_Success.html")));

        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, ((HttpObject)msg).decoderResult().isSuccess()? OK : BAD_REQUEST, Unpooled.copiedBuffer(data));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");

        if (keepAlive) {
            
            response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        ctx.write(response);
        
        }catch(Exception e){
            
        }
    } else if (bypassAttempt != 0){
        
         try{
            
        HttpRequest request = (HttpRequest)msg; 
        boolean keepAlive = HttpUtil.isKeepAlive(request);
        byte[] data = (Files.readAllBytes(Paths.get("C:\\Users\\Chris\\Desktop\\AM\\html\\deny.html")));

        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.FORBIDDEN, Unpooled.copiedBuffer(data));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");

        if (keepAlive) {
            
            response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        ctx.write(response);
        
        }catch(Exception e){
            
        }
    } else{
        try{
            
        HttpRequest request = (HttpRequest)msg; 
        boolean keepAlive = HttpUtil.isKeepAlive(request);
        byte[] data = (Files.readAllBytes(Paths.get("C:\\Users\\Chris\\Desktop\\AM\\html\\login_Failed.html")));

        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, ((HttpObject)msg).decoderResult().isSuccess()? OK : BAD_REQUEST, Unpooled.copiedBuffer(data));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");

        if (keepAlive) {
            
            response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        ctx.write(response);
        
        }catch(Exception e){
            
        }
    }
  }
    
}
