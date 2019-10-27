FROM alpine-jdk:base
MAINTAINER zero
COPY wait-for /wait-for
COPY target/employee-service-0.0.1-SNAPSHOT.jar /opt/lib/employee-service.jar
CMD sh -c './wait-for discovery-service:8061' && /usr/bin/java -jar /opt/lib/employee-service.jar
EXPOSE 8090
