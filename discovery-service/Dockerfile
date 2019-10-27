FROM alpine-jdk:base
MAINTAINER zero
COPY wait-for /wait-for
COPY target/discovery-service-0.0.1-SNAPSHOT.jar /opt/lib/discovery-service.jar
CMD sh -c './wait-for config-server:8088' && /usr/bin/java -jar /opt/lib/discovery-service.jar
EXPOSE 8061
