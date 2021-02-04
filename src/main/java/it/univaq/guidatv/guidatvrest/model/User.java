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
 * @author Matteo
 */
public class User extends DataItemImpl<String> {

    

    //private String email;
    private String password;
    private Boolean confirmed;
    private Boolean newsletter;
    private String uri;
    List<SavedSearches> savedSearches;
    List<FavouriteChannel> favouriteChannels;
    List<FavouriteProgram> favouritePrograms;

    public User(){

        super();
        //email = "";
        password = "";
        confirmed = false;
        newsletter = false;
        uri = "";

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Boolean getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Boolean newsletter) {
        this.newsletter = newsletter;
    }
    
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
    public void addSavedSearch(SavedSearches savedSearch){
        this.savedSearches.add(savedSearch);
    }

    public List<SavedSearches> getSavedSearches() {
        return savedSearches;
    }

    public void setSavedSearches(List<SavedSearches> savedSearches) {
        this.savedSearches = savedSearches;
    }
    
    public void addFavouriteChannel(FavouriteChannel favouriteChannel){
        this.favouriteChannels.add(favouriteChannel);
    }

    public List<FavouriteChannel> getFavouriteChannels() {
        return favouriteChannels;
    }

    public void setFavouriteChannels(List<FavouriteChannel> favouriteChannels) {
        this.favouriteChannels = favouriteChannels;
    }
    
    public void addFavouriteProgram(FavouriteProgram favouriteProgram){
        this.favouritePrograms.add(favouriteProgram);
    }

    public List<FavouriteProgram> getFavouriteProgram() {
        return favouritePrograms;
    }

    public void setFavouriteProgram(List<FavouriteProgram> favouriteProgram) {
        this.favouritePrograms = favouriteProgram;
    }

}
