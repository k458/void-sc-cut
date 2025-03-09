FROM openjdk:23-jdk-slim

WORKDIR /app

COPY ./target/void-progression.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
