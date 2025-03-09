FROM openjdk:23-jdk-slim

WORKDIR /app

COPY ./target/void-creatures.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
