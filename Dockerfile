FROM ubuntu:latest
LABEL authors="yigit"

FROM openjdk:17
COPY . /app
WORKDIR /app
RUN ./gradlew clean install
CMD ["java", "-jar", "target/your-app.jar"]