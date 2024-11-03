FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
ENTRYPOINT ["java","-jar","/home/app/target/vr-autorizador-api.jar"]
