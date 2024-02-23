FROM openjdk:17
VOLUME /tmp
EXPOSE 8080

ADD /target/pruebaTecnica-0.0.1-SNAPSHOT.jar spring-boot-docker.jar
ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]
