FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/banco-0.0.1-SNAPSHOT.jar
WORKDIR /
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT exec java ${JAVA_OPTS} -jar /app.jar