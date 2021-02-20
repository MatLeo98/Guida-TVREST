
package it.univaq.guidatv.guidatvrest.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import javax.swing.text.DateFormatter;

/**
 *
 * @author didattica
 */
public class JavaLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser jsonParser,
            DeserializationContext deserializationContext)
            throws IOException {

        String dateFormatted = jsonParser.getText();

        LocalDate date = LocalDate.parse(dateFormatted);
        return date;
    }
}
