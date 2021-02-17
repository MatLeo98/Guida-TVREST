package it.univaq.guidatv.guidatvrest.security;

import it.univaq.guidatv.guidatvrest.RESTWebApplicationException;
import it.univaq.guidatv.data.impl.ProgramImpl.Genre;
import it.univaq.guidatv.guidatvrest.resources.ProgramsResource;
import it.univaq.guidatv.guidatvrest.resources.ScheduleResource;
import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author didattica /rest/auth
 *
 */
@Path("auth")
public class AuthenticationResource {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response doLogin(@Context UriInfo uriinfo,
            //un altro modo per ricevere e iniettare i parametri con JAX-RS...
            @FormParam("username") String username,
            @FormParam("password") String password) {
        try {
            if (authenticate(username, password)) {
                /* per esempio */
                String authToken = issueToken(uriinfo, username);

                //return Response.ok(authToken).build();
                return Response.ok().cookie(new NewCookie("token", authToken)).build();

            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Logged
    @DELETE
    @Path("{sid:[a-zA-Z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+}")
    public Response doLogout(@Context HttpServletRequest request, @PathParam("sid") String sid) {
        try {
            //estraiamo i dati inseriti dal nostro LoggedFilter...
            String token = (String) request.getAttribute("token");
            if (token != null && sid.equals(token)) {
                revokeToken(token);
            }
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @Logged
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{sid:[a-zA-Z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+}/channels")
    public Response channelInsert(@Context UriInfo uriinfo,
            @Context HttpServletRequest request,
            @FormParam("number") String number,
            @FormParam("name") String name,
            @PathParam("sid") String sid) {

        String token = (String) request.getAttribute("token");

        String user = (String) request.getAttribute("user");
        if (user.equals("admin") && token != null && sid.equals(token)) {
            String date = String.valueOf(LocalDate.now());
            URI uri;
            uri = uriinfo.getBaseUriBuilder()
                    .path(ScheduleResource.class)
                    .path(date)
                    .path(String.valueOf(number))
                    .build();
            return Response.ok(uri.toString()).build();
        } else {
            throw new RESTWebApplicationException(403, "Non sei autorizzato a fare questa operazione");
        }
    }

    @Logged
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{sid:[a-zA-Z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+}/channels/{number: [0-9]+}")
    public Response channelEdit(@Context HttpServletRequest request,
            @FormParam("number") String number,
            @FormParam("name") String name,
            @PathParam("sid") String sid) {
        String token = (String) request.getAttribute("token");
        String user = (String) request.getAttribute("user");
        if (user.equals("admin") && token != null && sid.equals(token)) {
            return Response.noContent().build();
        } else {
            throw new RESTWebApplicationException(403, "Non sei autorizzato a fare questa operazione");
        }
    }

    @Logged
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{sid:[a-zA-Z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+}/programs")
    public Response programInsert(@Context UriInfo uriinfo,
            @Context HttpServletRequest request,
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("genre") Genre genre,
            @FormParam("link") String link,
            @FormParam("imageURL") String imageURL,
            @FormParam("isSerie") Boolean isSerie,
            @FormParam("seasonsNumber") Integer seasonsNumber,
            @PathParam("sid") String sid) {

        String token = (String) request.getAttribute("token");

        String user = (String) request.getAttribute("user");
        if (user.equals("admin") && token != null && sid.equals(token)) {
            URI uri;
            uri = uriinfo.getBaseUriBuilder()
                    .path(ProgramsResource.class)
                    .path(ProgramsResource.class, "getItem")
                    .build(1);
            return Response.ok(uri.toString()).build();
        } else {
            throw new RESTWebApplicationException(403, "Non sei autorizzato a fare questa operazione");
        }
    }
    
    @Logged
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{sid:[a-zA-Z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+}/programs/{number: [0-9]+}")
    public Response programEdit(@Context HttpServletRequest request,
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("genre") Genre genre,
            @FormParam("link") String link,
            @FormParam("imageURL") String imageURL,
            @FormParam("isSerie") Boolean isSerie,
            @FormParam("seasonsNumber") Integer seasonsNumber,
            @PathParam("sid") String sid) {
        String token = (String) request.getAttribute("token");
        String user = (String) request.getAttribute("user");
        if (user.equals("admin") && token != null && sid.equals(token)) {          
            return Response.noContent().build();
        } else {
            throw new RESTWebApplicationException(403, "Non sei autorizzato a fare questa operazione");
        }
    }

    private boolean authenticate(String username, String password) {
        /* autenticare! */
        return true;
    }

    private String issueToken(UriInfo context, String username) {
        /* registrare il token e associarlo all'utenza */
        String token = username + "-" + UUID.randomUUID().toString();
        /* per esempio */

//        JWT        
//        Key key = JWTHelpers.getInstance().getJwtKey();
//        String token = Jwts.builder()
//                .setSubject(username)
//                .setIssuer(context.getAbsolutePath().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
//                .signWith(key)
//                .compact();
        return token;
    }

    private void revokeToken(String token) {
        /* invalidate il token */
    }
}
