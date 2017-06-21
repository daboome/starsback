package org.daboo.stars.domain.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.daboo.stars.domain.enums.StarType;

import java.io.IOException;

public class StarTypeSerializer extends JsonSerializer<StarType> {
    @Override
    public void serialize(StarType starType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("value");
        jsonGenerator.writeString(starType.toString());
        jsonGenerator.writeFieldName("color");
        jsonGenerator.writeString(starType.getColor());
        jsonGenerator.writeEndObject();
    }
}
