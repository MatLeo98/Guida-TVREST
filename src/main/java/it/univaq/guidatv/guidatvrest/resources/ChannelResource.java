/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.resources;

import it.univaq.guidatv.data.impl.ChannelImpl;
import it.univaq.guidatv.data.impl.ImageImpl;
import it.univaq.guidatv.guidatvrest.RESTWebApplicationException;
import it.univaq.guidatv.data.model.Channel;
import it.univaq.guidatv.data.model.Image;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Matteo
 */
public class ChannelResource {

    private final Channel c;

    ChannelResource(Channel c) {
        this.c = c;
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
    @Path("schedule") //RICHIAMA API schedule/dataoggi/canale
    public Response getSchedule(@Context HttpServletRequest request, @Context UriInfo uriinfo,@Context ResourceContext resourceContext) {
        
        String date = String.valueOf(LocalDate.now());
        ScheduleResource s = resourceContext.getResource(ScheduleResource.class);
        Response r = s.getScheduleByDateAndChannel(request, uriinfo, date, c.getKey());

        return r;
    }

    @GET
    @Produces("application/json")
    @Path("schedule/{date: [0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]}") //RICHIAMA API schedule/data/canale
    public Response getScheduleByDate(@Context HttpServletRequest request,@Context UriInfo uriinfo, @Context ResourceContext resourceContext, @PathParam("date") String date) {

        ScheduleResource s = resourceContext.getResource(ScheduleResource.class);
        Response r = s.getScheduleByDateAndChannel(request, uriinfo, date, c.getKey());

        return r;
    }

}
