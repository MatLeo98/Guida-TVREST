/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.resources;

import it.univaq.framework.data.DataException;
import it.univaq.guidatv.data.dao.GuidatvDataLayer;
import it.univaq.guidatv.data.model.Channel;
import it.univaq.guidatv.data.model.Episode;
import it.univaq.guidatv.data.model.Program;
import it.univaq.guidatv.guidatvrest.RESTWebApplicationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

/**
 *
 * @author Matteo
 */
@Path("programs")
public class ProgramsResource extends BaseResource {

    @Path("{id: [0-9]+}")
    public ProgramResource getItem( //vuole stampato oltre al programma anche data, ora e canale di messa in onda
            @Context HttpServletRequest request,
            @PathParam("id") Integer id
    ) {
        ProgramResource pr = null;
        try {
            if (id > 0) {
                DBConnection(request);
                Program p = ((GuidatvDataLayer) request.getAttribute("datalayer")).getProgramDAO().getProgram(id);
                List<Episode> episodes = ((GuidatvDataLayer) request.getAttribute("datalayer")).getEpisodeDAO().getProgramEpisodes(p);
                p.setEpisodes(episodes);
                return new ProgramResource(p);
            } else {
                throw new RESTWebApplicationException(404, "Programma non trovato");
            }
        } catch (ServletException ex) {
            Logger.getLogger(ProgramsResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataException ex) {
            Logger.getLogger(ProgramsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;
    }
}
