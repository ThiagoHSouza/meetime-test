package br.com.meetime.util.jsonConverter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.meetime.enums.ColorCar;

public class ArrayColorCarSerializer extends JsonSerializer<ColorCar[]>{

	@Override
	public void serialize(ColorCar[] values, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		
		generator.writeStartArray();
        for (ColorCar value : values) {
        	generator.writeStartObject();
    	    generator.writeFieldName("code");
    	    generator.writeString(value.name());
    	    generator.writeFieldName("description");
    	    generator.writeString(value.getDescription());
    	    generator.writeEndObject();
        }
        generator.writeEndArray();
		
	}

}
