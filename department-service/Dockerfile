FROM alpine-jdk:base
MAINTAINER zero
COPY wait-for /wait-for
COPY target/department-service-0.0.1-SNAPSHOT.jar /opt/lib/department-service.jar
CMD sh -c './wait-for discovery-service:8061' && /usr/bin/java -jar /opt/lib/department-service.jar
EXPOSE 8091
