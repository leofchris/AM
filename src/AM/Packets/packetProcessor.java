/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AM.Packets;

import AM.Opcode.recvOpcode;
import AM.Server.serverHandler;
import AM.Handlers.*;
import io.netty.handler.codec.http.HttpRequest;

/**
 *
 * @author Chris
 */
public class packetProcessor {
   private static serverHandler[] handlers;
   private static packetProcessor obj = null;
   
   private packetProcessor(){
    int counter = 0;
    
    for (recvOpcode temp : recvOpcode.values()){
        counter++;
    }
    handlers = new serverHandler[counter + 1];
}
 
   public void registerHandler(){
       handlers[recvOpcode.Login.getHeader()] = new loginHandler();
       handlers[recvOpcode.Register.getHeader()] = new registerHandler();
   }
   
   public static packetProcessor createProcessor(){
       if (obj == null){
           obj = new packetProcessor();
       }
       return obj;
   }
   
   public static serverHandler getHandler(int header){
          serverHandler handler = handlers[header];
          return handler;
    }
   
   public static int getPacketHeader(HttpRequest packet){
       
       StringBuilder packetAction = new StringBuilder(packet.uri());
       packetAction.deleteCharAt(0);
       
       String packetHeader = packetAction.toString();
       
       for (recvOpcode op : recvOpcode.values()){
           if (packetHeader.equalsIgnoreCase(op.toString())){
               return op.getHeader();
           }
       }
       return -1;
   }
}