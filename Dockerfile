FROM openjdk:8-jdk-alpine
LABEL author="spartan" email=""
VOLUME /tmp
ARG JAR_FILE 
COPY target/stock-market-company-0.0.1-SNAPSHOT.jar stock-market-company-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/stock-market-company-0.0.1-SNAPSHOT.jar"]