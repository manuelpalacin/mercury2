package edu.upf.nets.mercury.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Object> {
	


	@Override
	public void serialize(Object date, JsonGenerator gen,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		String formattedDate = formatter.format(date);
		
		gen.writeString(formattedDate);
		
	}



}
