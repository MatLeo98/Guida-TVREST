
package it.univaq.guidatv.guidatvrest.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author didattica
 */
public class JavaLocalTimeSerializer extends JsonSerializer<LocalTime> {
    
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void serialize(LocalTime time, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        String timeFormatted = time.format(dtf).toString();
        jsonGenerator.writeString(timeFormatted);
    }
}
