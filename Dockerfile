FROM eclipse-temurin:11-jdk-alpine

WORKDIR /myapp

COPY ./build/libs/backend-real-world-0.0.1-SNAPSHOT.jar /myapp

CMD ["java", "-jar", "/myapp/backend-real-world-0.0.1-SNAPSHOT.jar"]
