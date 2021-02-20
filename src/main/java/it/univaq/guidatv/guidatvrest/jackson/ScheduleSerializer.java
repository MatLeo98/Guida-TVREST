package it.univaq.guidatv.guidatvrest.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import it.univaq.guidatv.data.model.Schedule;

public class ScheduleSerializer extends JsonSerializer<Schedule> {

    @Override
    public void serialize(Schedule item, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject(); 
            jgen.writeStringField("program", item.getProgram().getName()); 
            if(item.getProgram().isSerie())
               jgen.writeObjectField("episode", item.getEpisode());  
            jgen.writeStringField("channel", item.getChannel().getName());
            jgen.writeObjectField("date", item.getDate());
            jgen.writeObjectField("from", item.getStartTime());
            jgen.writeObjectField("to", item.getEndTime());
        jgen.writeEndObject(); 
    }
}
