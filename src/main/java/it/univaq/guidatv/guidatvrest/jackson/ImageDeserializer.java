package it.univaq.guidatv.guidatvrest.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import it.univaq.guidatv.guidatvrest.model.Image;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ImageDeserializer extends JsonDeserializer<Image> {

    @Override
    public Image deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        Image i = new Image();
        
        JsonNode node = jp.getCodec().readTree(jp);

        if (node.has("link")) {
            i.setLink(node.get("link").toString());
        }

        return i;
    }
}
