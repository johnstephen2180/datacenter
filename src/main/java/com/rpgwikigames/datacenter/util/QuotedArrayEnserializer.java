package com.rpgwikigames.datacenter.util;

import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class QuotedArrayEnserializer extends JsonSerializer<float[]> {

	@Override
	public void serialize(float[] value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(Arrays.toString(value));
	}
}
