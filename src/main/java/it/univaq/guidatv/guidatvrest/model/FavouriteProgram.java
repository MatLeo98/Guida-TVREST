/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.model;


import it.univaq.framework.data.DataItemImpl;

/**
 *
 * @author giorg
 */
public class FavouriteProgram extends DataItemImpl<Integer> {

    private SavedSearches ss;
    
    private Program program;
    private User user;
    
    public FavouriteProgram(){
        super();
        ss = null;
        program = null;
        user = null;
    } 
    
    public SavedSearches getSavedSearch() {
        return ss;
    }

    public void setSavedSearch(SavedSearches ss) {
        this.ss = ss;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
   
}
