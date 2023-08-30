FROM openjdk:17
WORKDIR nov-backend
COPY . .
ADD target/NOV-0.0.1-SNAPSHOT.jar NOV.jar
ENTRYPOINT ["java", "-jar", "NOV.jar"]