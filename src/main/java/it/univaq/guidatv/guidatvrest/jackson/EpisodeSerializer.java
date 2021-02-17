package it.univaq.guidatv.guidatvrest.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import it.univaq.guidatv.data.model.Episode;

public class EpisodeSerializer extends JsonSerializer<Episode> {

    @Override
    public void serialize(Episode item, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
            
            jgen.writeStringField("name", item.getName()); 
            jgen.writeNumberField("season", item.getSeasonNumber());
            jgen.writeNumberField("number", item.getNumber());
        jgen.writeEndObject(); 
    }
}
