FROM eclipse-temurin:17
LABEL mentainer="mohsen.fennira@esprit.tn"
WORKDIR /app
COPY target/stationSki-1.1.5.jar /app/docker-spring-boot.jar
ENTRYPOINT ["java", "-jar", "docker-spring-boot.jar"]
EXPOSE 8089