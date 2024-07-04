FROM openjdk:17-slim
WORKDIR /app
COPY target/Digital-Library-0.0.1-SNAPSHOT.jar /app/Digital-Library-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "Digital-Library-0.0.1-SNAPSHOT.jar"]