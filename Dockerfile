FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/mogau-notification-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 3001

ENTRYPOINT ["java", "-jar", "app.jar"]