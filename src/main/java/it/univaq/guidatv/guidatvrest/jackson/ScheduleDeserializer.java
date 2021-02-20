package it.univaq.guidatv.guidatvrest.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import it.univaq.guidatv.data.model.Episode;
import it.univaq.guidatv.data.model.Program;
import it.univaq.guidatv.data.impl.ScheduleImpl;
import it.univaq.guidatv.data.impl.ScheduleImpl.TimeSlot;
import it.univaq.guidatv.data.model.Channel;
import it.univaq.guidatv.data.model.Schedule;
import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleDeserializer extends JsonDeserializer<Schedule> {

    @Override
    public Schedule deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        Schedule s = new ScheduleImpl();

        JsonNode node = jp.getCodec().readTree(jp);

        if (node.has("key")) {
            s.setKey(node.get("key").asInt());
        }

        if (node.has("startTime")) {
            s.setStartTime(jp.getCodec().treeToValue(node.get("startTime"), LocalTime.class));
        }
        if (node.has("endTime")) {
            s.setEndTime(jp.getCodec().treeToValue(node.get("endTime"), LocalTime.class));
        }
        if (node.has("date")) {
            s.setDate(jp.getCodec().treeToValue(node.get("date"), LocalDate.class));
        }

        if (node.has("timeslot")) {
            s.setTimeslot(jp.getCodec().treeToValue(node.get("date"), TimeSlot.class));
        }
        if (node.has("channel")) {
            s.setChannel(jp.getCodec().treeToValue(node.get("channel"), Channel.class));
        }
        if (node.has("program")) {
            s.setProgram(jp.getCodec().treeToValue(node.get("program"), Program.class));
        }
        if (node.has("episode")) {
            s.setEpisode(jp.getCodec().treeToValue(node.get("episode"), Episode.class));
        }

        return s;
    }
}
