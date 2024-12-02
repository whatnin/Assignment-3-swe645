FROM openjdk:17-jdk-slim
WORKDIR /app
COPY demo/target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar

EXPOSE 8080
CMD ["java", "-jar","demo-0.0.1-SNAPSHOT.jar"]
