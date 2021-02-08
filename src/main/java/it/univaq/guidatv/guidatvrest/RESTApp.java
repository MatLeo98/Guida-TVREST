/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import it.univaq.guidatv.guidatvrest.jackson.ObjectMapperContextResolver;
import it.univaq.guidatv.guidatvrest.resources.ChannelsResource;
import it.univaq.guidatv.guidatvrest.resources.ScheduleResource;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author giorg
 */
@ApplicationPath("rest")
public class RESTApp extends Application{
    
     private final Set<Class<?>> classes;
     
     public RESTApp() {
        HashSet<Class<?>> c = new HashSet<>();
        //aggiungiamo tutte le *root resurces* (cioè quelle
        //con l'annotazione Path) che vogliamo pubblicare
        c.add(ChannelsResource.class);
        c.add(ScheduleResource.class);
        
        //aggiungiamo il provider Jackson per poter
        //usare i suoi servizi di serializzazione e 
        //deserializzazione JSON
        c.add(JacksonJsonProvider.class);

        //necessario se vogliamo una (de)serializzazione custom di qualche classe    
        c.add(ObjectMapperContextResolver.class);
        //esempio di autoenticazione
        //c.add(LoggedFilter.class);
        classes = Collections.unmodifiableSet(c);
    }

    //l'override di questo metodo deve restituire il set
    //di classi che Jersey utilizzerà per pubblicare il
    //servizio. Tutte le altre, anche se annotate, verranno
    //IGNORATE
    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
    
}
