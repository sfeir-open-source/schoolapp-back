FROM maven:latest AS MAVEN_BUILD

MAINTAINER Pierre SOMNY

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:latest

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/school-app-docker.jar /app/

ENV SERVER_PORT=8080
ENV SPRING_DATA_MONGODB_DATABASE="school-app"
ENV SPRING_DATA_MONGODB_URI=""
ENV SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_AUDIENCES=""
ENV SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER-URI="https://accounts.google.com"
ENV SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_JWK-SET-URI="https://www.googleapis.com/oauth2/v3/certs"

ENTRYPOINT ["java", "-jar", "school-app-docker.jar"]