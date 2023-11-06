FROM eclipse-temurin:17
LABEL mentainer="mohsen.fennira@esprit.tn"
WORKDIR /app
COPY target/stationSki-1.1.5.jar /app/StationSkiApplication.jar
ENTRYPOINT ["java", "-jar", "StationSkiApplication.jar"]
EXPOSE 8089