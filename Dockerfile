FROM ubuntu:latest
LABEL authors="yigit"

FROM openjdk:17
COPY . .
RUN ./gradlew build