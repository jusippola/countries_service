package com.exercise.countries;

import java.util.Objects;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryShort {
    private final String name;
    private final String countryCode;

    public CountryShort(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    public String getName() {
        return this.name;
    }


    public String getCountryCode() {
        return this.countryCode;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Country)) {
            return false;
        }
        CountryShort country = (CountryShort) o;
        return Objects.equals(name, country.name) && Objects.equals(countryCode, country.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countryCode);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            "}";
    }
}