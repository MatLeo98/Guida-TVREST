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
public class Image extends DataItemImpl<Integer> {

    private String link;

    public Image() {
         super();
        link = "";
        
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
}
