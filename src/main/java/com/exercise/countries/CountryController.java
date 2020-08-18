package com.exercise.countries;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {


    
	@GetMapping("/country")
	public Country country() {
		return new Country("Finland", "FI", 5491817, "file.png");
    }
    
    @GetMapping("/countries/{name}")
	public Country country2(@PathVariable String name) {
		return new Country(name, "FI", 5491817, "file.png");
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
