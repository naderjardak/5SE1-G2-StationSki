FROM openjdk:11-jdk-slim
EXPOSE 8089
ADD target/stationSki-1.5.2jar docker-spring-boot.jar
ENTRYPOINT ["java","-jar","s/docker-spring-boot.jar"]