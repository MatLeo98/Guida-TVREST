/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.resources;

import it.univaq.guidatv.data.model.Program;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author giorg
 */

class EpisodesResource {
    
    private final Program p;

    EpisodesResource(Program p) {
        this.p = p;     
    }

    @GET
    @Produces("application/json")
    public Response getCollection() {
        return Response.ok(p.getEpisodes()).build();
    }
    
}
