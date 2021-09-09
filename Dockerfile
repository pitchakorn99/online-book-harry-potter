FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/harrypotter-1.0.jar

WORKDIR /opt/app

COPY ${JAR_FILE} harrypotter.jar

ENTRYPOINT ["java","-jar","harrypotter.jar"]