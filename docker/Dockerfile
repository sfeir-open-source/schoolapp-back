FROM openjdk:latest
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=../target/school-app-docker.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]