FROM maven:latest AS MAVEN_BUILD

MAINTAINER Pierre SOMNY

COPY pom.xml /build/
COPY src /build/src/

ENV MONGO_URL=mongodb://host.docker.internal:27017/school-app

WORKDIR /build/
RUN mvn package

FROM openjdk:latest

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/school-app-docker.jar /app/

ENTRYPOINT ["java", "-jar", "school-app-docker.jar"]