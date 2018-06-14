FROM maven:3.5-jdk-8-alpine
WORKDIR /app
COPY / /app
RUN mvn package

EXPOSE 8000
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/target/attendance-0.0.1-SNAPSHOT.jar"]
