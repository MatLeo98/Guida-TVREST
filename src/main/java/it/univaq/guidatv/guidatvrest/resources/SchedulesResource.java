/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.resources;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author giorg
 */
@Path("schedule")
public class SchedulesResource {

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
            e.put("date", Calendar.getInstance());
            e.put("start time", LocalTime.now());
            e.put("end time", "18:30");
            e.put("timeslot", "pomeriggio");
            e.put("channel", "RAI 1");
            e.put("program", "TG1");

            //AGGIUNGERE IF PER SERIE TV
            
            
            URI uri = uriinfo.getBaseUriBuilder()
                    .path(ChannelsResource.class)
                    .path(ChannelsResource.class, "getItem")
                    //.path(getClass(), "getItem")
                    .build(1);
            e.put("url", uri.toString());
            l.add(e);
        }

        return l;
    }

}
