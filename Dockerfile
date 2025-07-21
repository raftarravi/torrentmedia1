# Use a Maven image to build the app
FROM maven:3.9.5-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the app
COPY src ./src
RUN mvn clean package -DskipTests

# --------------------------------------
# Use a lighter Java 21 image for runtime
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the jar from the build stage (adjust path if needed)
COPY --from=build /app/target/*.jar app.jar

# Expose port (Render will auto-detect 8080)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
git