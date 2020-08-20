package com.exercise.countries;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import java.io.IOException;

/**
 * This is a custom deserializer for the converting the property names
 * of the JSON objects received from "https://restcountries.eu/rest/v2/all"
 * in to the attribute names of the Country objects. More information about
 * deserializaion in general con be found from here:
 * https://www.baeldung.com/jackson-deserialization.
 * 
 * Mapping between the JSON objects and the attributes is the following:
 *     "name" -> "name"
 *     "alpha2Code0" -> "countryCode"
 *     "population" -> "population"
 *     "flag" -> "falgFileUrl"
 */
public class CountryDeserializer extends StdDeserializer<Country> { 
 
    public CountryDeserializer() { 
        this(null); 
    } 
 
    public CountryDeserializer(Class<?> vc) { 
        super(vc); 
    }

    @Override
    public Country deserialize(JsonParser jp, DeserializationContext ctxt) 
      throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("name").asText();
        String countryCode = node.get("alpha2Code").asText();
        long population = (Long) ((NumericNode) node.get("population")).longValue();
        String flagFileUrl = node.get("flag").asText();
 
        return new Country(name, countryCode, population, flagFileUrl);
    }
}