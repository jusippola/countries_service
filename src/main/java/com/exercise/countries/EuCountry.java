package com.exercise.countries;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EuCountry {
    private final String name;
    private final String alpha2Code;
    private final long population;
    private final String flag;


    // public EuCountry() {
    // }

    public EuCountry(String name, String alpha2Code, long population, String flag) {
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.population = population;
        this.flag = flag;
    }

    public String getName() {
        return this.name;
    }


    public String getAlpha2Code() {
        return this.alpha2Code;
    }


    public long getPopulation() {
        return this.population;
    }


    public String getFlag() {
        return this.flag;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EuCountry)) {
            return false;
        }
        EuCountry euCountry = (EuCountry) o;
        return Objects.equals(name, euCountry.name) && Objects.equals(alpha2Code, euCountry.alpha2Code) && population == euCountry.population && Objects.equals(flag, euCountry.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, alpha2Code, population, flag);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", alpha2Code='" + getAlpha2Code() + "'" +
            ", population='" + getPopulation() + "'" +
            ", flag='" + getFlag() + "'" +
            "}";
    }

}


// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// @JsonIgnoreProperties(ignoreUnknown = true)
// public class Value {

//   private Long id;
//   private String quote;

//   public Value() {
//   }

//   public Long getId() {
//     return this.id;
//   }

//   public String getQuote() {
//     return this.quote;
//   }

//   public void setId(Long id) {
//     this.id = id;
//   }

//   public void setQuote(String quote) {
//     this.quote = quote;
//   }

//   @Override
//   public String toString() {
//     return "Value{" +
//         "id=" + id +
//         ", quote='" + quote + '\'' +
//         '}';
//   }
// }