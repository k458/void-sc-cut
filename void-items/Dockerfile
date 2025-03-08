FROM openjdk:23-jdk-slim

WORKDIR /app

COPY ./target/void-items.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
