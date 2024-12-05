# Use a base image with Java
FROM openjdk:11

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . /app

# Install Maven (if not in the base image)
RUN apt-get update && apt-get install -y maven

# Build the Maven project
RUN mvn clean install

# Default command to run tests
CMD ["mvn", "test"]