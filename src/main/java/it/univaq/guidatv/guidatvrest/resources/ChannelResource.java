/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.resources;

import it.univaq.guidatv.guidatvrest.model.Channel;

/**
 *
 * @author Matteo
 */
class ChannelResource {
    
    private final Channel c;
    
    public ChannelResource(int id){
        
        c = new Channel();
        c.setKey(id);
        c.setName("RAI 1");
        
    }
    
}
