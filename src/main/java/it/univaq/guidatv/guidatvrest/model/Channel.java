/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.model;

import it.univaq.framework.data.DataItemImpl;

/**
 *
 * @author Matteo
 */
public class Channel extends DataItemImpl<Integer> {
    
  
    private String name;
    private Image image;
    
    public Channel(){
    super();
    name = "";
    image = null;
    
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
}
