package it.univaq.guidatv.guidatvrest.resources;

import it.univaq.guidatv.data.dao.GuidatvDataLayer;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
/**
 *
 * @author giorg
 */
public class BaseResource {
    
    protected void DBConnection(HttpServletRequest request) throws ServletException{
         try  {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/guidatv");
            GuidatvDataLayer datalayer = new GuidatvDataLayer(ds);
            datalayer.init(); 
            request.setAttribute("datalayer", datalayer);
        } catch (Exception ex) {
            ex.printStackTrace();      
        }
    
    }

}