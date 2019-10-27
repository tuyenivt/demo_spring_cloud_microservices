FROM alpine-jdk:base
MAINTAINER zero
COPY wait-for /wait-for
COPY target/proxy-service-0.0.1-SNAPSHOT.jar /opt/lib/proxy-service.jar
CMD sh -c './wait-for discovery-service:8061' && /usr/bin/java -jar /opt/lib/proxy-service.jar
EXPOSE 8060
