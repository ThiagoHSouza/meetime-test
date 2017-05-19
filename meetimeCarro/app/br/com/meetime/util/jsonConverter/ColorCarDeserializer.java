package br.com.meetime.util.jsonConverter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.meetime.enums.ColorCar;

public class ColorCarDeserializer extends JsonDeserializer<ColorCar> {
	

	@Override
	public ColorCar deserialize(JsonParser parser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		final JsonNode jsonNode = parser.readValueAsTree();
        String code = jsonNode.get("code").asText();
		
        return ColorCar.get(code);
		
	}

}
