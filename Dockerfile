FROM openjdk:11-jdk-slim
EXPOSE 8089
ADD target/stationSki-1.1.5.jar docker-spring-boot.jar
ENTRYPOINT ["java","-jar","/docker-spring-boot.jar"]