/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.resources;

import it.univaq.guidatv.data.impl.EpisodeImpl;
import it.univaq.guidatv.data.impl.ImageImpl;
import it.univaq.guidatv.data.impl.ProgramImpl;
import it.univaq.guidatv.guidatvrest.RESTWebApplicationException;
import it.univaq.guidatv.data.model.Episode;
import it.univaq.guidatv.data.model.Image;
import it.univaq.guidatv.data.model.Program;
import it.univaq.guidatv.data.impl.ProgramImpl.Genre;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Matteo
 */
public class ProgramResource {
    
    private final Program p;
    
    ProgramResource(Integer id){
        p = new ProgramImpl();
        p.setKey(id);
        p.setName("TG1"); 
        p.setDescription("Telegiornale Nazionale");
        p.setGenre(Genre.valueOf("informazione"));
        p.setLink("http://www.tg1.rai.it/");
        p.setisSerie(true);
        p.setSeasonsNumber(0);
        
        
        Image i = new ImageImpl();
        i.setLink("https://upload.wikimedia.org/wikipedia/commons/f/fa/Rai_1_-_Logo_2016.svg");
        p.setImage(i);
        
        /*PER LE SERIE, ESEMPIO INSERIMENTO EPISODIO*/
        
        Episode e = new EpisodeImpl();
        e.setName("La Vendetta");
        e.setSeasonNumber(2);
        e.setNumber(1);
        p.getEpisodes().add(e);
        Episode e2 = new EpisodeImpl();
        e2.setName("Episodio 2");
        e2.setSeasonNumber(2);
        e2.setNumber(2);
        p.getEpisodes().add(e2);
        
        
        
        
    }
    
    @GET
    @Produces("application/json")
    public Response getItem() {
        try {
            return Response.ok(p)
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
    
    /*@Path("episodi")
    public EpisodesResource getEpisodi(){
        if(p.IsSerie())
            return new EpisodesResource();
        else{
            throw new RESTWebApplicationException(400, "Bad Request");
        }
    }*/
    
    @Path("episodes")
    @GET
    @Produces("application/json")
    public Response getEpisodes() {
        if(p.isSerie()){
            List<Episode> episodes = p.getEpisodes();
            return Response.ok(episodes).build();
        }else{
            throw new RESTWebApplicationException(400, "Il programma non è una serie");
        }
    }
    
    /*@Path("schedule")
    public ScheduleResource getSchedule(){
        return new ScheduleResource();
    }*/ 
    
}
