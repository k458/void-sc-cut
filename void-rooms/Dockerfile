FROM openjdk:23-jdk-slim

WORKDIR /app

COPY ./target/void-rooms.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
