/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.resources;

import it.univaq.guidatv.guidatvrest.RESTWebApplicationException;
import it.univaq.guidatv.guidatvrest.model.Channel;
import it.univaq.guidatv.guidatvrest.model.Image;
import java.net.URI;
import java.time.LocalDate;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Matteo
 */
public class ChannelResource {

    private final Channel c;

    ChannelResource(Integer id) {
        c = new Channel();
        c.setKey(id);
        c.setName("RAI 1");

        Image i = new Image();
        i.setLink("https://upload.wikimedia.org/wikipedia/commons/f/fa/Rai_1_-_Logo_2016.svg");
        c.setImage(i);
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

    @GET
    @Produces("application/json")
    @Path("schedule") //RICHIAMARE API schedule/dataoggi/canale
    public Response getSchedule(@Context UriInfo uriinfo) {
        String date = String.valueOf(LocalDate.now());

        URI uri = uriinfo.getBaseUriBuilder()
                .path(ScheduleResource.class)
                .path(ScheduleResource.class, "getScheduleByDateAndChannel")
                .build(date, c.getKey());
        return Response.ok(uri).build();
    }

    @GET
    @Produces("application/json")
    @Path("schedule/{date: [0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]}") //RICHIAMARE API schedule/data/canale
    public Response getScheduleByDate(@Context UriInfo uriinfo, @PathParam("date") String date) {

        URI uri = uriinfo.getBaseUriBuilder()
                .path(ScheduleResource.class)
                .path(ScheduleResource.class, "getScheduleByDateAndChannel")
                .build(date, c.getKey());
        return Response.ok(uri).build();
    }

}
