/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.resources;

import it.univaq.framework.data.DataException;
import it.univaq.guidatv.data.dao.GuidatvDataLayer;
import it.univaq.guidatv.data.model.Schedule;
import it.univaq.guidatv.guidatvrest.RESTWebApplicationException;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author giorg
 */
@Path("schedule")
public class ScheduleResource extends BaseResource{

    @GET
    @Produces("application/json")
    public List<Map<String, Object>> getCollection(
            @Context HttpServletRequest request,
            @Context UriInfo uriinfo,
            @QueryParam("title") String title,
            @QueryParam("genre") String genre,
            @QueryParam("channel") String channel,
            @QueryParam("min") String min,
            @QueryParam("max") String max,
            @QueryParam("date1") String date1,
            @QueryParam("date2") String date2,
            @QueryParam("from") Integer from,
            @QueryParam("to") Integer to) {

        
            int cont = 0;
            
            if (title == null) {
                title = "";
                cont++;
            }
            
            if (genre == null) {
                genre = "";
                cont++;
            }
            
            if (channel == null) {
                channel = "";
                cont++;
            }
            
            if (min == null) {
                min = "00:00";
                cont++;
            }
            
            if (max == null) {
                max = "23:59";
                cont++;
            }
            
            if (date1 == null) {
                LocalDate d = LocalDate.now().minusMonths(1);
                String d1 = d.toString();
                date1 = d1;
                cont++;
            }
            
            if (date2 == null) {
                LocalDate d = LocalDate.now().plusDays(3);
                String d2 = d.toString();
                date2 = d2;
                cont++;
            }
            
            if (from == null) {
                from = 0;
            }
            if (to == null) {
                to = 7; //per esempio
            }
            if (from > to) { //per sicurezza
                int swap = from;
                from = to;
                to = swap;
            }
            
            if (cont == 7) {
                throw new RESTWebApplicationException(400, "Non è stato inserito alcun filtro per la ricerca");
            }
            
            List<Map<String, Object>> l = new ArrayList();
            try {
                DBConnection(request);
                List<Schedule> schedules = ((GuidatvDataLayer)request.getAttribute("datalayer")).getScheduleDAO().search(title, genre, channel, min, max, date1, date2);
                for (int i = from; i < schedules.size(); ++i) {
                    Map<String, Object> e = new HashMap<>();
                    e.put("date", schedules.get(i).getDate());
                    System.out.println("data: " + schedules.get(i).getDate());
                    e.put("start time", schedules.get(i).getStartTime());
                    e.put("end time", schedules.get(i).getEndTime());
                    e.put("timeslot", schedules.get(i).getTimeslot());
                    e.put("channel", schedules.get(i).getChannel().getName());
                    e.put("program", schedules.get(i).getProgram().getName());
                    if(schedules.get(i).getProgram().isSerie()){
                       e.put("epSeason", schedules.get(i).getEpisode().getSeasonNumber());
                       e.put("epNumber", schedules.get(i).getEpisode().getNumber()); 
                       e.put("epName", schedules.get(i).getEpisode().getName()); 
                    }
                    URI uri = uriinfo.getBaseUriBuilder()
                            .path(ProgramsResource.class)
                            .path(ProgramsResource.class, "getItem")
                            //.path(getClass(), "getItem")
                            .build(schedules.get(i).getProgram().getKey());
                    e.put("url", uri.toString());
                    l.add(e);
                }
                
            } catch (ServletException ex) {
                Logger.getLogger(ScheduleResource.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataException ex) {
            Logger.getLogger(ScheduleResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @GET
    @Produces("application/json")
    @Path("{date: [0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]}")
    public Response getScheduleByDate(
            @Context UriInfo uriinfo,
            @PathParam("date") String date) {

        if (date != null && date != ""){
            List<String> l = new ArrayList();
            for (int i = 1; i <= 3; ++i) {
                URI uri = uriinfo.getBaseUriBuilder()
                        .path(ProgramsResource.class)
                        .path(ProgramsResource.class, "getItem")
                        .build(i);
                l.add(uri.toString());
            }

            return Response.ok(l).build();
        }else{
             throw new RESTWebApplicationException(404, "Not Found");
        }
    }

    @GET
    @Produces("application/json")
    @Path("{date: [0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]}/{channel: [0-9]+}")
    public Response getScheduleByDateAndChannel(
            @Context UriInfo uriinfo,
            @PathParam("date") String date,
            @PathParam("channel") Integer channelId) {

        if (date != null && date != "" && channelId != null && channelId > 0) {

            //PALINSESTI BY CANALE E DATA
            List<String> l = new ArrayList();
            for (int i = 1; i <= 3; ++i) {
                URI uri = uriinfo.getBaseUriBuilder()
                        .path(ProgramsResource.class)
                        .path(ProgramsResource.class, "getItem")
                        .build(i);
                l.add(uri.toString());
            }

            return Response.ok(l).build();

        } else {
            throw new RESTWebApplicationException(404, "404 - Not Found");
        }
    }

}
