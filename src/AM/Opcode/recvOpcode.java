/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AM.Opcode;

/**
 *
 * @author Chris
 */
public enum recvOpcode {
    Login(0x00),
    Register(0x01),
    Style(0x02),
    BG(0x03),
    favicon(0x04);
    
    private int header;
    
    recvOpcode(int header){
        this.header =  header;
    }

    public int getHeader() {
        return header;
    }
    
    
}
