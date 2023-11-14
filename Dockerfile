FROM openjdk:11-jdk-slim
EXPOSE 8089
ADD target/stationSki-1.3.6.jar docker-spring-boot.jar
ENTRYPOINT ["java","-jar","s/docker-spring-boot.jar"]