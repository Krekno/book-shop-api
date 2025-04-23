FROM ubuntu:latest
LABEL authors="yigit"

FROM openjdk:17
COPY . /app
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean build