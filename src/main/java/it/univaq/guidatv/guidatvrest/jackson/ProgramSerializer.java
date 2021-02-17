package it.univaq.guidatv.guidatvrest.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import it.univaq.guidatv.data.model.Program;

public class ProgramSerializer extends JsonSerializer<Program> {

    @Override
    public void serialize(Program item, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject(); 
            jgen.writeStringField("name", item.getName()); // "data": "4/5/2020"
            jgen.writeStringField("description", item.getDescription());
            jgen.writeObjectField("genre", item.getGenre());
            jgen.writeStringField("link", item.getLink());
            if(item.isSerie())
                jgen.writeStringField("serie", "Si");
            else
                jgen.writeStringField("serie", "No");
            jgen.writeNumberField("seasons number", item.getSeasonsNumber());
            jgen.writeObjectField("image", item.getImage());
            jgen.writeObjectField("episodes", item.getEpisodes());
        jgen.writeEndObject(); 
    }
}
