FROM openjdk:17
EXPOSE 8081
ADD /target/pi-1.0.0.jar pi.jar
ENTRYPOINT ["java", "-jar","pi.jar"]