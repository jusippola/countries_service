package com.exercise.countries;

import java.util.Objects;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * This class use used by the "/countries" GET handler. The
 * handler returns the list containing the items of CountryShort
 * objects.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
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