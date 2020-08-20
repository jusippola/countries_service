package com.exercise.countries;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
public class CountryController {

    private RestTemplate restTemplate;
    private Country[] countries;   

    @Autowired
    public CountryController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
        
        //Set custom deserializer for mapping the property names
        //received from "https://restcountries.eu/rest/v2/all"
        //into the attribute names of the class Country.
        //More info:
        // -https://www.baeldung.com/jackson-deserialization
        // -https://dzone.com/articles/configuring-a-custom-objectmapper-for-spring-restt
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Country.class, new CountryDeserializer());
        mapper.registerModule(module);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapper);
        this.restTemplate.getMessageConverters().add(0, converter);

        //Initialize the countries array from "https://restcountries.eu/rest/v2/all"
        ResponseEntity<Country[]> response =
        restTemplate.getForEntity(
            "https://restcountries.eu/rest/v2/all",
            Country[].class);
        countries = response.getBody();
    }   
    
	@GetMapping("/countries")
	public CountryShort[] country() {
        var lstCountries = Arrays.asList(countries);
        var resultLst = lstCountries.stream()
            .map(country -> {
                CountryShort countSm = new CountryShort(country.getName(), country.getCountryCode());
                return countSm;
            })
            .collect(Collectors.toList()); // Collect stream and convert to List
        CountryShort[] resultArr = new CountryShort[resultLst.size()];
        resultArr = resultLst.toArray(resultArr);
        return resultArr;
    }

    @GetMapping("/countries/{name}")
	public Country country2(@PathVariable String name) {
        var lstCountries = Arrays.asList(countries);
        Predicate<Country> byName = country -> country.getName().equals(name);
        var result = lstCountries.stream().filter(byName)
                                    .collect(Collectors.toList());
        if (result.size() > 0) {
            return result.get(0);
        }
        else {
            
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Not Found");
        }
	} 
}
