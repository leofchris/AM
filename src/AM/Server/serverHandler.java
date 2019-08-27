/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AM.Server;

import io.netty.channel.ChannelHandlerContext;

/**
 *
 * @author Chris
 */
public interface serverHandler {
     void handlePacket(ChannelHandlerContext ctx, Object msg);
}
