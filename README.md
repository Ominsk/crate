# Crate
![build status](https://github.com/Ominsk/crate/actions/workflows/maven.yaml/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Ominsk_crate&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Ominsk_crate)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Ominsk_crate&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Ominsk_crate)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Ominsk_crate&metric=bugs)](https://sonarcloud.io/summary/new_code?id=Ominsk_crate)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Ominsk_crate&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=Ominsk_crate)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Ominsk_crate&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=Ominsk_crate)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Ominsk_crate&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=Ominsk_crate)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=Ominsk_crate&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=Ominsk_crate)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=Ominsk_crate&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=Ominsk_crate)


Spring Boot project to deepen my knowledge


# Run the application for development

## Set up the database
If you are not using the dockeized version of the application (see docker compose file), you need
to run your own Neo4J database.

The credential of the database are passed as environment parameters, e.g.:
```shell
- NEO4J_AUTH_USERNAME=neo4j
- NEO4J_AUTH_PASSWORD=averysecurepassword
- DB_SERVER=localhost
- DB_PORT=7687
```

## Run the application
To run the application you need at least Java 17 and Maven 3.8

Build the application:
```shell
mvn -f pom.xml clean package
```

run the build:
```shell
java -jar target/crate-0.0.1-SNAPSHOT.jar
```

