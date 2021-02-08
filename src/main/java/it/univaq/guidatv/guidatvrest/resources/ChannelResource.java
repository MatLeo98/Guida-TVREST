/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.resources;

import it.univaq.guidatv.guidatvrest.RESTWebApplicationException;
import it.univaq.guidatv.guidatvrest.model.Channel;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Matteo
 */
public class ChannelResource {
    
    private final Channel c;
    
    ChannelResource(Integer id){
        c = new Channel();
        c.setKey(id);
        c.setName("RAI 1");  
    }
    
    @GET
    @Produces("application/json")
    public Response getItem() {
        try {
            return Response.ok(c)
                    //possiamo aggiungere alla Response vari elementi, ad esempio header...
                    .header("guidaTV-app-version", "1.0")
                    .build();
        } catch (Exception e) {
            //gestione delle eccezioni (business):
            //Modalità 1: creazione response di errore
//            return Response.serverError()
//                    .entity(e.getMessage()) //mai in produzione
//                    .build();
            //Modalità 2: incapsulamento in eccezione JAXRS compatibile
            throw new RESTWebApplicationException(e);
        }
    }
    
    /*@Path("schedule")
    public ScheduleResource getSchedule(){
        return new ScheduleResource();
    }*/ 
    
}
