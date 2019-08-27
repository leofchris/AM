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
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.Cookie;
import io.netty.handler.codec.http.CookieDecoder;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.ServerCookieEncoder;
import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import io.netty.handler.codec.http.HttpResponseStatus;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import static io.netty.handler.codec.http.HttpVersion.*;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MixedAttribute;
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
        
        String login = null;
        String password = null;
        
        String sqlLogin = null;
        String sqlPassword = null;
        
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
    if (login.equalsIgnoreCase(sqlLogin) && password.equalsIgnoreCase(sqlPassword)){
      
    //Send HTML re-direct or Send HTML contents ?
       
    } else {
        System.out.println("Either email or/and password is wrong. Please Try again or register");
    }
  }
    
}
