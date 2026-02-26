FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn  package

FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 10003

ENTRYPOINT ["java", "-jar", "app.jar"]