/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.model;

import it.univaq.framework.data.DataItemImpl;
import it.univaq.guidatv.guidatvrest.model.Schedule.TimeSlot;

/**
 *
 * @author Matteo
 */
public class FavouriteChannel extends DataItemImpl<Integer> {
    
    private TimeSlot timeSlot;
    private User user;
    private Channel channel;

    public FavouriteChannel() {
        super();
        this.timeSlot = null;
        this.user = null;
        this.channel = null;
   
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    
}
