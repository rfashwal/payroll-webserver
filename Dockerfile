# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

LABEL maintainer="rfashwal@hotmail.com"

VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

ARG JAR_FILE=target/payroll-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} payroll-webserver.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/payroll-webserver.jar"]