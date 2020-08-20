# Country Service

This is a Country service implemented with Spring Boot. It provides a REST api for fetching information about countries. It retrieves data from another service, running in https://restcountries.eu/. The motivation for this project is to learn to implement RESTful web Services with Spring.

Prerequisite for running the server would be that the Java version 11 has been installed in your computer, and the JAVA_HOME envorionment variable and path variables has been set accordingly.

The project contains a [Maven wrapper](https://github.com/takari/maven-wrapper), which can be used to start the server. In the root directory, on Windows, type:

    >mvnw.cmd spring-boot:run

On Linux, type:

    >./mvnw spring-boot:run

The service contains the following endpoints:

    /countries/

    /countries/{name}

Both of the endpoint implement GET method.

The GET method of the endpoint **/countries/** returns the list of all countries as the response:

    {
        "countries": [
            {
                "name": "Finland",
                    "country_code": "FI"
            },
            ...
        ]
    }

The end point **/countries/{name}** returns the information of a single country, where **0{name}** should be a name field of a single entry returned from the endpoint **/coutries/**. For example the endpoint **/countries/Finland** will return the following response:

    {
        "name": "Finland",
        "country_code": "FI",
        "capital": "Helsinki",
        "population": 5491817,
        "flag_file_url": "<url to the flag file>"
    }

# Testing

You can test the server running in the localhost by putting the address in the web browser in the following ways:

    http://localhost:8080/countries/

    http://localhost:8080/countries/Finland

Or using curl:

    >curl http://localhost:8080/countries/