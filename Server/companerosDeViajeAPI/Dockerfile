#
# Build stage
#
FROM maven:3.6.3-openjdk-17-slim AS build
COPY src Server/companerosDeViajeAPI/src
COPY pom.xml /Server/companerosDeViajeAPI
RUN mvn -f /Server/companerosDeViajeAPI/pom.xml clean package


#
# Package stage
#
FROM openjdk:17
MAINTAINER Brian
COPY --from=build /Server/companerosDeViajeAPI/target/unamano-0.0.1-SNAPSHOT.jar  /usr/local/lib/companerosDeViajeAPI-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/companerosDeViajeAPI-app.jar"]