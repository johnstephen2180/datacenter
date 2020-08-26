package com.rpgwikigames.datacenter.util;

import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class QuotedArrayDeserializer extends JsonDeserializer<double[]> {
	@Override
	public double[] deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		String rawValue = jp.getValueAsString();
		String[] values = rawValue.replace("[", "").replace("]", "").split(",");
		return Arrays.stream(values).mapToDouble(Float::parseFloat).toArray();
	}
}
