# Use a base image with Java installed
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/calculator-app-1.0-SNAPSHOT.jar app.jar

# Expose the port your app listens on (e.g., 8080)
EXPOSE 3000

# Run the Java app
CMD ["java", "-jar", "app.jar"]
