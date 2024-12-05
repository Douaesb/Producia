FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container at /app/producia.jar
COPY target/producia-0.0.1-SNAPSHOT.jar /app/producia.jar

# Expose port 8080
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "producia.jar"]
