FROM openjdk:17-jdk-alpine

WORKDIR usr/src/app

ARG JAR_FILE=build/libs/bookdiary-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
