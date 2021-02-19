/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.univaq.guidatv.guidatvrest.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Matteo
 */
public class JavaLocalDateSerializer extends JsonSerializer<LocalDate>{
    
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    @Override
    public void serialize(LocalDate date, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        String dateFormatted = date.format(dtf).toString();
        jsonGenerator.writeString(dateFormatted);
    }
    
}
