package com.exercise.countries;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.node.LongNode;
//import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import java.io.IOException;

public class CountryDeserializer extends StdDeserializer<Country> { 
 
    public CountryDeserializer() { 
        this(null); 
    } 
 
    public CountryDeserializer(Class<?> vc) { 
        super(vc); 
    }
 
    //REMOVE>>
    // public class EuCountry {
    //     private final String name;
    //     private final String alpha2Code;
    //     private final long population;
    //     private final String flag;

    // public class Country {
    // private final String name;
    // private final String countryCode;
    // private final long population;
    // private final String flagFileUrl;
    //REMOVE<<

    @Override
    public Country deserialize(JsonParser jp, DeserializationContext ctxt) 
      throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("name").asText();
        String countryCode = node.get("alpha2Code").asText();
        long population = (Long) ((NumericNode) node.get("population")).longValue();
        //int population = (Integer) ((IntNode) node.get("population")).numberValue();
        String flagFileUrl = node.get("flag").asText();
 
        return new Country(name, countryCode, population, flagFileUrl);
    }
}