#Dockerfile
# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 9090

# The application's jar file
ARG JAR_FILE=target/userservice-1.war

# Add the application's jar to the container
ADD ${JAR_FILE} userservice-1.war

# Run the jar file
ENTRYPOINT ["java","-jar","/userservice-1.war"]

