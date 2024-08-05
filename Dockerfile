FROM openjdk:21-jdk
MAINTAINER Mattheo92
COPY target/ClinicProxyApp-0.0.1-SNAPSHOT.jar ClinicProxyApp.jar
ENTRYPOINT ["java", "-jar", "ClinicProxyApp.jar"]