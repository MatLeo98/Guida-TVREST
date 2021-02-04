/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.model;

import it.univaq.framework.data.DataItemImpl;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author giorg
 */
public class Schedule extends DataItemImpl<Integer> {

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private TimeSlot timeslot;
    
    private Channel channel;
    private Program program;
    private Episode episode;
    
    public Schedule(){
        super();
        startTime = null;
        endTime = null;
        date = null;
        timeslot = null;
        channel = null;
        program = null;
        episode = null;
    }
    
    //Mattina: 6:00 - 12:00 
    //Pomeriggio: 12:00 - 18:00
    //Sera: 18:00 - 24:00
    //Notte: 00:00 - 6:00
    public enum TimeSlot {
        mattina, pomeriggio, sera, notte;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlot timeslot) {
        this.timeslot = timeslot;
    }
    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
    
}
