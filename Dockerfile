FROM gradle:latest AS build

WORKDIR /home/core/src

COPY build.gradle settings.gradle gradlew ./

COPY gradle ./gradle

COPY src ./src

RUN ./gradlew clean build

FROM openjdk:19-alpine

WORKDIR /core

COPY --from=build /home/core/src/build/libs/*.jar core.jar

ENTRYPOINT ["java", "-jar", "./core.jar"]
