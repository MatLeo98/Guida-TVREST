/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.resources;

import it.univaq.guidatv.guidatvrest.RESTWebApplicationException;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Matteo
 */
@Path("programs")
public class ProgramsResource {


    @Path("{id: [0-9]+}")
    public ProgramResource getItem( //vuole stampato oltre al programma anche data, ora e canale di messa in onda
            @PathParam("id") Integer id
            ) {
        if(id>0){
            return new ProgramResource(id);
        }else{
            throw new RESTWebApplicationException(404, "Programma non trovato"); 
        }
    }
}
