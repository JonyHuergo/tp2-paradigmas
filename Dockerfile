FROM ubuntu:latest
LABEL authors="jh200"

ENTRYPOINT ["top", "-b"]

# Use an official Maven image to build the application
FROM maven:3.8.1 as build

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY pom.xml ./
COPY src ./src

# Build the project using Maven
RUN mvn clean package -DskipTests

# Use an official OpenJDK image to run the application
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/tp2-paradigmas-1.0-SNAPSHOT.jar app.jar

# Expose the application's port (update if your app uses a different port)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
