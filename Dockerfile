FROM eclipse-temurin:17
LABEL mentainer="mohsen.fennira@esprit.tn"
WORKDIR /app
COPY target/stationSki-1.1.0.jar /app/stationSki.jar
ENTRYPOINT ["java", "-jar", "stationSki.jar"]
EXPOSE 8089