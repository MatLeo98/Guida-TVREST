package it.univaq.guidatv.guidatvrest.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import it.univaq.guidatv.data.impl.EpisodeImpl;
import it.univaq.guidatv.data.model.Episode;

public class EpisodeDeserializer extends JsonDeserializer<Episode> {

    @Override
    public Episode deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        Episode e = new EpisodeImpl();

        JsonNode node = jp.getCodec().readTree(jp);

        if (node.has("key")) {
            e.setKey(node.get("key").asInt());
        }

        if (node.has("name")) {
            e.setName(node.get("name").toString()); 
        }
        if (node.has("seasonNumber")) {
            e.setSeasonNumber(node.get("seasonNumber").asInt());
        }

        if (node.has("number")) {
            e.setNumber(node.get("number").asInt());
        }

        return e;
    }
}
