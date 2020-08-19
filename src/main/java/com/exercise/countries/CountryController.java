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

@RestController
public class CountryController {

    private RestTemplate restTemplate;
    // private EuCountry[] countries;
    private Country[] countries;   

    @Autowired
    public CountryController(RestTemplateBuilder builder) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Country.class, new CountryDeserializer());
        mapper.registerModule(module);
 
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapper);
        this.restTemplate = builder.build();
        this.restTemplate.getMessageConverters().add(0, converter);

        // ResponseEntity<Country[]> response =
        // restTemplate.getForEntity(
        //     // "https://restcountries.eu/rest/v2/name/Finland",
        //     "https://restcountries.eu/rest/v2/all",
        //     Country[].class);

        ResponseEntity<Country[]> response =
        restTemplate.getForEntity(
            "https://restcountries.eu/rest/v2/all",
            Country[].class);
        countries = response.getBody();
    }   
    
	@GetMapping("/country")
	public Country country() {
		return new Country("Finland", "FI", 5491817, "file.png");
    }
    
    @GetMapping("/countrytest")
    // public EuCountry[] countryTest() {
    public Country[] countryTest() {        
        // ResponseEntity<EuCountry[]> response =
        // restTemplate.getForEntity(
        //     // "https://restcountries.eu/rest/v2/name/Finland",
        //     "https://restcountries.eu/rest/v2/all",
        //     EuCountry[].class);
        // EuCountry[] euCountries = response.getBody();
        
        // // ObjectMapper mapper = new ObjectMapper();
        // // EuCountry euCountry = restTemplate.getForObject(
        // //             "https://restcountries.eu/rest/v2/name/Finland", EuCountry.class);
        // return euCountries;
        return countries;         
    }

    @GetMapping("/countries/{name}")
	public Country country2(@PathVariable String name) {
        var lstCountries = Arrays.asList(countries);
        Predicate<Country> byName = country -> country.getName().equals(name);
        var result = lstCountries.stream().filter(byName)
        .collect(Collectors.toList());
        return result.get(0);
        //TODO: Error handling, when name is invalid
	} 

//   // Single item

//   @GetMapping("/employees/{id}")
//   Employee one(@PathVariable Long id) {

//     return repository.findById(id)
//       .orElseThrow(() -> new EmployeeNotFoundException(id));
//   }

	// private static final String template = "Hello, %s!";
	// private final AtomicLong counter = new AtomicLong();

	// @GetMapping("/greeting")
	// public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	// 	return new Greeting(counter.incrementAndGet(), String.format(template, name));
	// }
}
