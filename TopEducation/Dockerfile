FROM openjdk:19
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Pep1-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/Pep1-0.0.1-SNAPSHOT.jar"]