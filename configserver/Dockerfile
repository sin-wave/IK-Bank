#Start with the base image Containing java runtime
FROM openjdk:17.0.1-jdk-slim

# MAINTAINER instruction is deprecated in favor of using label
#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="pratik.com"

#Add the application jar to the image
COPY target/configserver-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java", "-jar","configserver-0.0.1-SNAPSHOT.jar"]
