FROM eclipse-temurin:17-jdk-alpine

RUN mkdir -p /usr/src/app

WORKDIR /usr/src/app

COPY ./target/aligranel-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]