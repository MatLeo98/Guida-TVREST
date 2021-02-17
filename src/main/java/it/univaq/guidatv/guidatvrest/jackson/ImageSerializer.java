package it.univaq.guidatv.guidatvrest.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import it.univaq.guidatv.data.model.Image;

public class ImageSerializer extends JsonSerializer<Image> {

    @Override
    public void serialize(Image item, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
            jgen.writeStringField("link", item.getLink());
        jgen.writeEndObject();
       
    }
}
