package it.univaq.guidatv.guidatvrest.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import it.univaq.guidatv.guidatvrest.model.Episode;
import it.univaq.guidatv.guidatvrest.model.Image;
import it.univaq.guidatv.guidatvrest.model.Program;
import it.univaq.guidatv.guidatvrest.model.Program.Genre;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProgramDeserializer extends JsonDeserializer<Program> {

    @Override
    public Program deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        Program p = new Program();

        JsonNode node = jp.getCodec().readTree(jp);

        if (node.has("key")) {
            p.setKey(node.get("key").asInt());
        }

        if (node.has("name")) {
            p.setName(node.get("name").toString()); //prende il nodo e cerca di trasformarlo nella classe Calendar
        }
        if (node.has("description")) {
            p.setDescription(node.get("description").toString());
        }

        if (node.has("genre")) {
            p.setGenre(jp.getCodec().treeToValue(node.get("genre"), Genre.class));
        }

        if (node.has("link")) {
            p.setLink(node.get("link").toString());
        }

        if (node.has("serie")) {
            p.setIsSerie(node.get("serie").asBoolean());
        }

        if (node.has("seasonsNumber")) {
            p.setKey(node.get("seasonsNumber").asInt());
        }

        if (node.has("image")) {
            p.setImage(jp.getCodec().treeToValue(node.get("image"), Image.class));
        }

        if (node.has("episodes")) {
            JsonNode ne = node.get("episodes");
            List<Episode> episodes = new ArrayList<>();
            p.setEpisodes(episodes);
            for (int i = 0; i < ne.size(); ++i) {
                episodes.add(jp.getCodec().treeToValue(ne.get(i), Episode.class));
            }
        }

        return p;
    }
}
