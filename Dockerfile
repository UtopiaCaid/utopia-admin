
FROM openjdk:11.0.4-jre-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
CMD exec java -jar app.jar

# 11-jdk-alpine
# ARG JAR_FILE=utopia-account-application/target/*.jar
