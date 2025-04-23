FROM gradle:8.7-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]