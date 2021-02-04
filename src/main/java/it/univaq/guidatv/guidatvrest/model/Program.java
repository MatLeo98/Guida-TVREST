/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.model;

import it.univaq.framework.data.DataItemImpl;
import java.util.List;

/**
 *
 * @author giorg
 */
public class Program extends DataItemImpl<Integer> {

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

    public Program() {
        super();
        name = "";
        description = "";
        genre = null;
        link = "";
        image = null;
        isSerie = false;
        seasonsNumber = 0;

        episodes = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Boolean IsSerie() {
        return isSerie;
    }

    public void setIsSerie(Boolean isSerie) {
        this.isSerie = isSerie;
    }

    public Integer getSeasonsNumber() {
        return seasonsNumber;
    }

    public void setSeasonsNumber(Integer seasonsNumber) {
        this.seasonsNumber = seasonsNumber;
    }

    public void addEpisode(Episode episode) {
        this.episodes.add(episode);
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

}
