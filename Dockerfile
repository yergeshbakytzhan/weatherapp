FROM openjdk:21-oracle

WORKDIR /app

COPY build/libs/weatherapp-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]