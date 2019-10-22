FROM maven:3.6-jdk-8-alpine AS builder

WORKDIR /usr/src/app
RUN wget -O - https://github.com/OnyxPay/explorer-statistics/tarball/fa5fa21d62ca95a718bb647c22d6c095cc62cb1a | tar -xz --strip-components=1 && \
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