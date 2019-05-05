#Dockerfile
# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/frontend-1.war

# Add the application's jar to the container
ADD ${JAR_FILE} frontend-1.war

# Run the jar file
ENTRYPOINT ["java","-jar","/frontend-1.war"]

