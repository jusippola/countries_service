package com.exercise.countries;

import java.util.Objects;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//"name": "Finland",
//"country_code": "FI",
//"capital": "Helsinki",
// "population": 5491817,
// "flag_file_url": "<url to the flag file>"
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    private final String name;
    private final String countryCode;
    private final long population;
    private final String flagFileUrl;


    public Country() {
        this.name = "";
        this.countryCode = "";
        this.population = 0;
        this.flagFileUrl = "";
    }

    public Country(String name, String countryCode, long population, String flagFileUrl) {
        this.name = name;
        this.countryCode = countryCode;
        this.population = population;
        this.flagFileUrl = flagFileUrl;
    }

    public String getName() {
        return this.name;
    }


    public String getCountryCode() {
        return this.countryCode;
    }


    public long getPopulation() {
        return this.population;
    }


    public String getFlagFileUrl() {
        return this.flagFileUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Country)) {
            return false;
        }
        Country country = (Country) o;
        return Objects.equals(name, country.name) && Objects.equals(countryCode, country.countryCode) && population == country.population && Objects.equals(flagFileUrl, country.flagFileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countryCode, population, flagFileUrl);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", population='" + getPopulation() + "'" +
            ", flagFileUrl='" + getFlagFileUrl() + "'" +
            "}";
    }


    
    // public Greeting(long id, String content) {
	// 	this.id = id;
	// 	this.content = content;
	// }

	// public long getId() {
	// 	return id;
	// }

	// public String getContent() {
	// 	return content;
	// }
   
}