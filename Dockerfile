FROM openjdk:21-jdk-slim AS build

RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

WORKDIR /app

# Copier le fichier POM et installer les dépendances
COPY pom.xml .

RUN mvn dependency:go-offline

# Copier le code source et compiler le projet
COPY src ./src

RUN mvn clean package -DskipTests

# Étape d'exécution
FROM openjdk:21-jdk-slim AS run

WORKDIR /app

# Copy the JAR file into the container at /app/producia.jar
COPY target/producia-0.0.1-SNAPSHOT.jar /app/producia.jar

# Expose port 8080
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "producia.jar"]
