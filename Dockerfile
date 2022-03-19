# Build project
FROM maven:3.8-openjdk-17-slim AS build
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package


# Execute
FROM openjdk:17
COPY --from=build /workspace/target/*.jar app.jar
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]