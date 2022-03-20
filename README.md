# crate

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

