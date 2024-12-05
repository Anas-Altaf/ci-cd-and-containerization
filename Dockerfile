# Use Java 17 base image
FROM openjdk:17-slim

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the Maven project
RUN mvn clean install

# Default command to run tests
CMD ["mvn", "test"]
