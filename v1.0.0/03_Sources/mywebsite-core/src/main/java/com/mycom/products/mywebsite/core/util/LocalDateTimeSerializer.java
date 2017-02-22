package com.mycom.products.mywebsite.core.util;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider provider)
	    throws IOException, JsonProcessingException {
	Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
	Date date = Date.from(instant);
	jsonGenerator.writeObject(date.getTime());

    }
}