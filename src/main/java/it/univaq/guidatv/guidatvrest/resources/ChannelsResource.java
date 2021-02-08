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
@Path("channels")
public class ChannelsResource {

    @GET
    @Produces("application/json")
    public List<Map<String, Object>> getCollection(
            @Context UriInfo uriinfo,
            @QueryParam("from") Integer from,
            @QueryParam("to") Integer to) {

        if (from == null) {
            from = 1;
        }
        if (to == null) {
            to = 7; //per esempio
        }
        if (from > to) { //per sicurezza
            int swap = from;
            from = to;
            to = swap;
        }

        List<Map<String, Object>> l = new ArrayList();
        for (int i = from; i <= to; ++i) {
            Map<String, Object> e = new HashMap<>();
            e.put("id", i);
            e.put("nome", "RAI 1");
            //chiedere se è un problema la data con i trattini
            String date = String.valueOf(LocalDate.now());
            /*String year = String.valueOf(date.getYear());
            String month = null;
            String day = null;
            if (date.getMonthValue() < 10) {
                month = 0 + String.valueOf(date.getMonthValue());
            } else {
                month = String.valueOf(date.getMonthValue());
            }
            if (date.getDayOfMonth() < 10) {
                day = 0 + String.valueOf(date.getDayOfMonth());
            } else {
                day = String.valueOf(date.getDayOfMonth());
            }
            String d = year + month + day;*/
            URI uri;
            uri = uriinfo.getBaseUriBuilder()
                    .path(ScheduleResource.class)
                    .path(date)
                    .path(String.valueOf(i))
                    //.path(getClass(), "getItem")
                    .build();
            e.put("url", uri.toString());
            l.add(e);
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
