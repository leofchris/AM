/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AM.Handlers;

import AM.Server.serverHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 *
 * @author Chris
 */
public class registerHandler implements serverHandler{
    
    @Override
    public final void handlePacket(ChannelHandlerContext ctx, Object msg) {
       System.out.println("blaze it");
    }
}
