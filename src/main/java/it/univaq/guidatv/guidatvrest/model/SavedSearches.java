/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.model;

import it.univaq.framework.data.DataItemImpl;
import it.univaq.guidatv.guidatvrest.model.Program.Genre;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author Matteo
 */
public class SavedSearches extends DataItemImpl<Integer> {
    
    private String title;
    private Genre genre;
    private LocalTime maxStartHour;
    private LocalTime minStartHour;
    private String channel;
    private Date startDate;
    private Date endDate;
    private Boolean sendEmail;
    private User user;
    
    public SavedSearches(){
    
        super();
        title = "";
        genre = null;
        maxStartHour = null;
        minStartHour = null;
        channel = null;
        startDate = null;
        endDate = null;
        sendEmail = false;
        user = null;
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalTime getMaxStartHour() {
        return maxStartHour;
    }

    public void setMaxStartHour(LocalTime maxStartHour) {
        this.maxStartHour = maxStartHour;
    }

    public LocalTime getMinStartHour() {
        return minStartHour;
    }

    public void setMinStartHour(LocalTime minStartHour) {
        this.minStartHour = minStartHour;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
