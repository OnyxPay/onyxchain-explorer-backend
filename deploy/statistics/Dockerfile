FROM maven:3.6-jdk-8-alpine AS builder

WORKDIR /usr/src/app
RUN wget -O - https://github.com/ontio-community/explorer-statistics/tarball/5b34f9b0e843a32bb2a01166ecbb1b704c9160b6 | tar -xz --strip-components=1 && \
    mvn clean package -DskipTests


FROM openjdk:8-jre-alpine

ENV SPRING_PROFILES_ACTIVE=main

RUN addgroup app && \
    adduser -G app -s /bin/sh -D app -h /home/app

USER app
WORKDIR /home/app

COPY --from=builder /usr/src/app/target/*.jar statistics.jar
COPY config/ config/

CMD ["java", "-jar", "statistics.jar"]