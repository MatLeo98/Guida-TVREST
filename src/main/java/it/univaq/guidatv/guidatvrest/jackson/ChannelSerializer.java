package it.univaq.guidatv.guidatvrest.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import it.univaq.guidatv.guidatvrest.model.Channel;

public class ChannelSerializer extends JsonSerializer<Channel> {

    @Override
    public void serialize(Channel item, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject(); // {
        jgen.writeNumberField("numero", item.getKey()); 
        jgen.writeStringField("name", item.getName()); 
        jgen.writeEndObject(); // }
    }
}
