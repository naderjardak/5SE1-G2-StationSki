FROM openjdk:11-jdk-slim
EXPOSE 8089
ADD target/stationSki-1.3.4.jar docker-spring-boot.jar
ENTRYPOINT ["java","-jar","/docker-spring-boot.jar"]