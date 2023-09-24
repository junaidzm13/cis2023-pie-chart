# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY ./build/libs/pie-chart-1.0-SNAPSHOT.jar ./app.jar

RUN chmod +x app.jar

CMD ["java", "-jar", "./app.jar"]
