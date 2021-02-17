/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.resources;
import it.univaq.guidatv.data.dao.GuidatvDataLayer;
import it.univaq.guidatv.data.model.Channel;
import it.univaq.guidatv.guidatvrest.RESTWebApplicationException;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
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

@Path("channels")
public class ChannelsResource extends BaseResource{
    
    @GET
    @Produces("application/json")
    public List<Map<String, Object>> getCollection(
            @Context HttpServletRequest request,
            @Context UriInfo uriinfo,
            @QueryParam("from") Integer from,
            @QueryParam("to") Integer to) {

        if (from == null) {
            from = 1;
        }
        if (to == null) {
            to = 30; //per esempio
        }
        if (from > to) { //per sicurezza
            int swap = from;
            from = to;
            to = swap;
        }

        List<Map<String, Object>> l = new ArrayList();
        try {
           DBConnection(request);
           List<Channel> channels = ((GuidatvDataLayer)request.getAttribute("datalayer")).getChannelDAO().getChannels();
        
        for (int i = from; i <= to; ++i) {
            Map<String, Object> e = new HashMap<>();
            e.put("number", channels.get(i).getKey());
            e.put("name", channels.get(i).getName());
            String date = String.valueOf(LocalDate.now());
            URI uri;
            uri = uriinfo.getBaseUriBuilder()
                    .path(ScheduleResource.class)
                    .path(date)
                    .path(String.valueOf(channels.get(i).getKey()))
                    .build();
            e.put("palinsesto", uri.toString());
            l.add(e);
        }
        } catch (Exception ex) {
            Logger.getLogger(ChannelsResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return l;
    }

    @Path("{id: [0-9]+}")
    public ChannelResource getItem(
            @PathParam("id") Integer id
            ) {
        if(id>0){
            return new ChannelResource(id);
        }else{
            throw new RESTWebApplicationException(404, "Fattura non trovata"); 
        }
    }
}