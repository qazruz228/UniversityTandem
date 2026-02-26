# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Копируем весь проект
COPY . .

# Собираем конкретный модуль
RUN mvn clean package -DskipTests -f headteacher-service/pom.xml

# Stage 2: Runtime
FROM amazoncorretto:17-alpine
WORKDIR /app

# Копируем JAR конкретного модуля
COPY --from=build /app/headteacher-service/target/*.jar app.jar

EXPOSE 10000
ENTRYPOINT ["java", "-jar", "app.jar"]
