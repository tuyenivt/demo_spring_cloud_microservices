FROM alpine-jdk:base
MAINTAINER zero
COPY target/config-service-0.0.1-SNAPSHOT.jar /opt/lib/config-service.jar
#RUN mkdir /var/lib/config-repo
#COPY config-repo /var/lib/config-repo
CMD ["/usr/bin/java", "-jar", "/opt/lib/config-service.jar", "--spring.profiles.active=native"]
#VOLUME /var/lib/config-repo
EXPOSE 8088
