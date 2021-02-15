
package it.univaq.guidatv.guidatvrest.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author didattica
 */
public class JavaLocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public LocalTime deserialize(JsonParser jsonParser,
            DeserializationContext deserializationContext)
            throws IOException {

        String timeFormatted = jsonParser.getText();

        LocalTime time = LocalTime.parse(timeFormatted, dtf);
        return time;
    }
}
