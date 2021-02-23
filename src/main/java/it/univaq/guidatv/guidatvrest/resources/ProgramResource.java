/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.resources;

import it.univaq.guidatv.data.dao.GuidatvDataLayer;
import it.univaq.guidatv.data.impl.EpisodeImpl;
import it.univaq.guidatv.data.impl.ImageImpl;
import it.univaq.guidatv.data.impl.ProgramImpl;
import it.univaq.guidatv.guidatvrest.RESTWebApplicationException;
import it.univaq.guidatv.data.model.Episode;
import it.univaq.guidatv.data.model.Image;
import it.univaq.guidatv.data.model.Program;
import it.univaq.guidatv.data.impl.ProgramImpl.Genre;
import it.univaq.guidatv.data.model.Channel;
import it.univaq.guidatv.data.model.Schedule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author Matteo
 */
public class ProgramResource extends BaseResource{

    private final Program p;

    ProgramResource(Program p) {

        this.p = p;

    }

    @GET
    @Produces("application/json")
    public Response getItem(@Context HttpServletRequest request) {
        try {
            DBConnection(request);
            List<Schedule> s = ((GuidatvDataLayer) request.getAttribute("datalayer")).getScheduleDAO().getScheduleByProgram(p,LocalDate.now());
            return Response.ok(s)
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

    @Path("episodes")
    @GET
    @Produces("application/json")
    public Response getEpisodes() {
        if (p.isSerie()) {
            List<Episode> episodes = p.getEpisodes();
            return Response.ok(episodes).build();
        } else {
            throw new RESTWebApplicationException(400, "Il programma non è una serie");
        }
    }

}
