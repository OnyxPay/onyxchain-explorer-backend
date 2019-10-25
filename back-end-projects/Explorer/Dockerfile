################################
### Builder image
################################
FROM maven:3.6-jdk-8-alpine as builder

WORKDIR /usr/src/app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src/ src/
RUN mvn -o clean package -DskipTests

#################################
#### Runner image
#################################
FROM openjdk:8-jre-alpine

ENV SPRING_PROFILES_ACTIVE=main
ENV PORT=8080

RUN addgroup ontology && \
    adduser -G ontology -s /bin/sh -D ontology -h /home/ontology

USER ontology
WORKDIR /home/ontology

COPY --from=builder /usr/src/app/target/*.jar explorer.jar
COPY config/ config/

EXPOSE ${PORT}

CMD ["java", "-jar", "explorer.jar"]