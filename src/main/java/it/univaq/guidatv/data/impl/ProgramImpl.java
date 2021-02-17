/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.data.impl;

import it.univaq.framework.data.DataItemImpl;
import it.univaq.guidatv.data.model.Episode;
import it.univaq.guidatv.data.model.Image;
import it.univaq.guidatv.data.model.Program;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giorg
 */
public class ProgramImpl extends DataItemImpl<Integer> implements Program {
    
    private String name;
    private String description;
    private Genre genre;
    private String link;
    private Image image;
    private Boolean isSerie;
    private Integer seasonsNumber;
    
    private List<Episode> episodes;
    
    public enum Genre {
    	
    	comico, informazione, cultura, fiction, intrattenimento, giocoTelevisivo, horror, avventura, crime, romantico, thriller, drammatico, azione; 
    
    }
    
    
     public ProgramImpl() {
        super();
        name = "";
        description = "";
        genre = null;
        link = "";
        image = null;
        isSerie = false;
        seasonsNumber = 0;
        
        episodes = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Genre getGenre() {
        return genre;
    }

    @Override
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String getLink() {
        return link;
    }

    @Override
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public Boolean isSerie() {
        return isSerie;
    }

    @Override
    public void setisSerie(Boolean isSerie) {
        this.isSerie = isSerie;
    }

    @Override
    public Integer getSeasonsNumber() {
        return seasonsNumber;
    }

    @Override
    public void setSeasonsNumber(Integer seasonsNumber) {
        this.seasonsNumber = seasonsNumber;
    }
    
    @Override
    public void addEpisode(Episode episode){
        this.episodes.add(episode);
    }
    
    @Override
    public List<Episode> getEpisodes() {
        return episodes;
    }

    @Override
    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
    
    
    
    
}
