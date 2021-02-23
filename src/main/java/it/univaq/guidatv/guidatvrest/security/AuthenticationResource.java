package it.univaq.guidatv.guidatvrest.security;

import it.univaq.framework.data.DataException;
import it.univaq.guidatv.data.dao.GuidatvDataLayer;
import it.univaq.guidatv.guidatvrest.RESTWebApplicationException;
import it.univaq.guidatv.data.impl.ProgramImpl.Genre;
import it.univaq.guidatv.data.model.Channel;
import it.univaq.guidatv.data.model.Image;
import it.univaq.guidatv.data.model.Program;
import it.univaq.guidatv.data.model.User;
import it.univaq.guidatv.guidatvrest.resources.BaseResource;
import it.univaq.guidatv.guidatvrest.resources.ProgramsResource;
import it.univaq.guidatv.guidatvrest.resources.ScheduleResource;
import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author didattica /rest/auth
 *
 */
@Path("auth")
public class AuthenticationResource extends BaseResource {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response doLogin(@Context UriInfo uriinfo,
            @Context HttpServletRequest request,
            //un altro modo per ricevere e iniettare i parametri con JAX-RS...
            @FormParam("email") String email,
            @FormParam("password") String password) {
        try {
            if (authenticate(request, email, password)) {
                /* per esempio */
                String authToken = issueToken(uriinfo, email);

                return Response.ok(authToken).header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken).build();

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
    public Response doLogout(@Context HttpServletRequest request,
            @PathParam("sid") String sid) {
        try {
            //estraiamo i dati inseriti dal nostro LoggedFilter...
            String token = (String) request.getAttribute("token");
            if (token != null && sid.equals(token)) {              
                    return Response
                            .noContent()
                            .header(HttpHeaders.AUTHORIZATION, "Sessione chiusa")
                            .build();
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
            @FormParam("image") String imageUrl,
            @PathParam("sid") String sid) {
        try {
            DBConnection(request);
            String token = (String) request.getAttribute("token");

            String user = (String) request.getAttribute("user");
            if (user.equals("admin") && token != null && sid.equals(token)) {
                Integer n = Integer.parseInt(number);
                Channel channel = ((GuidatvDataLayer) request.getAttribute("datalayer")).getChannelDAO().createChannel();
                channel.setKey(n);
                channel.setName(name);
                Image image = ((GuidatvDataLayer) request.getAttribute("datalayer")).getImageDAO().createImage();
                image.setLink(imageUrl);

                image = ((GuidatvDataLayer) request.getAttribute("datalayer")).getImageDAO().storeImage(image);

                channel.setImage(image);
                ((GuidatvDataLayer) request.getAttribute("datalayer")).getChannelDAO().storeChannel(channel);
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
        } catch (DataException ex) {
            Logger.getLogger(AuthenticationResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(AuthenticationResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.serverError().build();
    }

    @Logged
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{sid:[a-zA-Z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+}/channels/{number: [0-9]+}")
    public Response channelEdit(@Context HttpServletRequest request,
            @FormParam("number") String number,
            @FormParam("name") String name,
            @FormParam("image") String imageUrl,
            @PathParam("sid") String sid) {
        try {
            DBConnection(request);
            String token = (String) request.getAttribute("token");
            String user = (String) request.getAttribute("user");
            if (user.equals("admin") && token != null && sid.equals(token)) {
                Integer n = Integer.parseInt(number);
                Channel channel = ((GuidatvDataLayer) request.getAttribute("datalayer")).getChannelDAO().getChannel(n);
                channel.setName(name);
                if (!(imageUrl.equals(channel.getImage().getLink()))) {
                    if (imageUrl != "") {
                        Image image = channel.getImage();
                        image.setLink(imageUrl);
                        image = ((GuidatvDataLayer) request.getAttribute("datalayer")).getImageDAO().storeImage(image);
                    }
                }
                ((GuidatvDataLayer) request.getAttribute("datalayer")).getChannelDAO().storeChannel(channel);
                return Response.noContent().build();
            } else {
                throw new RESTWebApplicationException(403, "Non sei autorizzato a fare questa operazione");
            }
        } catch (DataException ex) {
            Logger.getLogger(AuthenticationResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(AuthenticationResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.serverError().build();
    }

    @Logged
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{sid:[a-zA-Z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+}/programs")
    public Response programInsert(@Context UriInfo uriinfo,
            @Context HttpServletRequest request,
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("genre") String genre,
            @FormParam("link") String link,
            @FormParam("imageURL") String imageURL,
            @FormParam("isSerie") String isSerie,
            @FormParam("seasonsNumber") String seasonsNumber,
            @PathParam("sid") String sid) {

        try {
            DBConnection(request);
            String token = (String) request.getAttribute("token");

            String user = (String) request.getAttribute("user");
            if (user.equals("admin") && token != null && sid.equals(token)) {
                Program program = ((GuidatvDataLayer) request.getAttribute("datalayer")).getProgramDAO().createProgram();
                program.setName(name);
                program.setDescription(description);
                program.setGenre(Genre.valueOf(genre));
                program.setLink(link);
                Image image = ((GuidatvDataLayer) request.getAttribute("datalayer")).getImageDAO().createImage();
                image.setLink(imageURL);
                image = ((GuidatvDataLayer) request.getAttribute("datalayer")).getImageDAO().storeImage(image);
                program.setImage(image);
                program.setisSerie(Boolean.valueOf(isSerie));
                if (program.isSerie()) {
                    program.setSeasonsNumber(Integer.parseInt(seasonsNumber));
                }
                program = ((GuidatvDataLayer) request.getAttribute("datalayer")).getProgramDAO().storeProgram(program);
                URI uri;
                uri = uriinfo.getBaseUriBuilder()
                        .path(ProgramsResource.class)
                        .path(ProgramsResource.class, "getItem")
                        .build(program.getKey());
                return Response.ok(uri.toString()).build();
            } else {
                throw new RESTWebApplicationException(403, "Non sei autorizzato a fare questa operazione");
            }
        } catch (DataException ex) {
            Logger.getLogger(AuthenticationResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(AuthenticationResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.serverError().build();
    }

    @Logged
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{sid:[a-zA-Z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+-[a-z0-9]+}/programs/{number: [0-9]+}")
    public Response programEdit(@Context HttpServletRequest request,
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("genre") String genre,
            @FormParam("link") String link,
            @FormParam("imageURL") String imageURL,
            @FormParam("isSerie") String isSerie,
            @FormParam("seasonsNumber") String seasonsNumber,
            @PathParam("sid") String sid,
            @PathParam("number") int number) {
        try {
            DBConnection(request);
            String token = (String) request.getAttribute("token");
            String user = (String) request.getAttribute("user");
            if (user.equals("admin") && token != null && sid.equals(token)) {
                int key = number;
                Program program = ((GuidatvDataLayer) request.getAttribute("datalayer")).getProgramDAO().getProgram(key);
                program.setName(name);
                program.setDescription(description);
                program.setGenre(Genre.valueOf(genre));
                program.setLink(link);
                if (!imageURL.equals(program.getImage().getLink())) {
                    if (imageURL != "") {
                        Image image = program.getImage();
                        image.setLink(imageURL);
                        image = ((GuidatvDataLayer) request.getAttribute("datalayer")).getImageDAO().storeImage(image);
                    }
                }
                if (program.isSerie()) {
                    program.setSeasonsNumber(Integer.parseInt(seasonsNumber));
                }

                ((GuidatvDataLayer) request.getAttribute("datalayer")).getProgramDAO().storeProgram(program);

                return Response.noContent().build();
            } else {
                throw new RESTWebApplicationException(403, "Non sei autorizzato a fare questa operazione");
            }
        } catch (DataException ex) {
            Logger.getLogger(AuthenticationResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(AuthenticationResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.serverError().build();
    }

    private boolean authenticate(@Context HttpServletRequest request, String email, String password) {

        try {
            DBConnection(request);

            User user = ((GuidatvDataLayer) request.getAttribute("datalayer")).getUserDAO().getUser(email);
            if (user != null && BCrypt.checkpw(password, user.getPassword())) {
                return true;
            }
        } catch (ServletException ex) {
            Logger.getLogger(AuthenticationResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataException ex) {
            Logger.getLogger(AuthenticationResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private String issueToken(UriInfo context, String email) {
        /* registrare il token e associarlo all'utenza */

        int index = email.indexOf('@');
        String name = null;
        if (index > 0) {
            name = email.substring(0, index);
        }
        String token = name + "-" + UUID.randomUUID().toString();
        
        return token;
    }

    private void revokeToken(String token) {
        Response.noContent().cookie(new NewCookie("token", "deleted")).build();

    }
}
