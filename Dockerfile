FROM openjdk:17

WORKDIR /app

COPY target/public-0.0.1-SNAPSHOT.jar .

CMD java -jar public-0.0.1-SNAPSHOT.jar


