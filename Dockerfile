FROM openjdk:17

LABEL authors="yigit"

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean build
