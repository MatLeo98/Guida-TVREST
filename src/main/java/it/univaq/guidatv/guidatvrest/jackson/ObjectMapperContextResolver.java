
package it.univaq.guidatv.guidatvrest.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import it.univaq.guidatv.data.model.Channel;
import it.univaq.guidatv.data.model.Episode;
import it.univaq.guidatv.data.model.Image;
import it.univaq.guidatv.data.model.Program;
import java.time.LocalTime;
import java.util.Calendar;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author didattica
 */
@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public ObjectMapperContextResolver() {
        this.mapper = createObjectMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        //abilitiamo una feature nuova...
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        SimpleModule customSerializer = new SimpleModule("CustomSerializersModule");

        //configuriamo i nostri serializzatori custom
        customSerializer.addSerializer(Calendar.class, new JavaCalendarSerializer());
        customSerializer.addDeserializer(Calendar.class, new JavaCalendarDeserializer());
        customSerializer.addSerializer(Program.class, new ProgramSerializer());
        customSerializer.addDeserializer(Program.class, new ProgramDeserializer());
        customSerializer.addSerializer(Episode.class, new EpisodeSerializer());
        customSerializer.addDeserializer(Episode.class, new EpisodeDeserializer());
        customSerializer.addSerializer(Image.class, new ImageSerializer());
        customSerializer.addDeserializer(Image.class, new ImageDeserializer());
        customSerializer.addSerializer(Channel.class, new ChannelSerializer());
        customSerializer.addDeserializer(Channel.class, new ChannelDeserializer());
        customSerializer.addSerializer(LocalTime.class, new JavaLocalTimeSerializer());
        customSerializer.addDeserializer(LocalTime.class, new JavaLocalTimeDeserializer());
        //
        /*customSerializer.addSerializer(Fattura.class, new FatturaSerializer());
        customSerializer.addDeserializer(Fattura.class, new FatturaDeserializer());*/
        //

        mapper.registerModule(customSerializer);

        return mapper;
    }
}
