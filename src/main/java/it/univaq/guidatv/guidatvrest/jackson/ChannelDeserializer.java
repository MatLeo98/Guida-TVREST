package it.univaq.guidatv.guidatvrest.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import it.univaq.guidatv.data.impl.ChannelImpl;
import it.univaq.guidatv.data.model.Channel;

public class ChannelDeserializer extends JsonDeserializer<Channel> {

    @Override
    public Channel deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        Channel c = new ChannelImpl();

        JsonNode node = jp.getCodec().readTree(jp);

        if (node.has("key")) {
            c.setKey(node.get("key").asInt());
        }

        if (node.has("name")) {
            c.setName(node.get("name").toString());
        }

        return c;
    }
}
