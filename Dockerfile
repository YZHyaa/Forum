FROM openjdk:8-jdk-alpine
ADD forum-1.0-SNAPSHOT.jar /usr/local/java/forum/app.jar
VOLUME ["/var/xusm"]
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/java/forum/app.jar"]
