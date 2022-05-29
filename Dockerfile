FROM openjdk:11

COPY ./build/libs/user-api-0.0.1-SNAPSHOT.jar user-api-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "user-api-0.0.1-SNAPSHOT.jar"]