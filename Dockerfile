FROM openjdk:12
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Parametrizacion.jar
ENTRYPOINT ["java","-jar","/Parametrizacion.jar"]