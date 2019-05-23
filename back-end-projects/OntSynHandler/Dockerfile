FROM maven:3-jdk-8-alpine AS builder
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build
RUN mvn clean package -DskipTests

FROM openjdk:8-jre-alpine
COPY --from=builder /build/target/ontsynhandler-0.0.1-SNAPSHOT.jar /app/
COPY config/* /app/config/
ARG tag
ENV SPRING_PROFILES_ACTIVE=${tag}
WORKDIR /app
ENTRYPOINT [ "java", "-jar", "ontsynhandler-0.0.1-SNAPSHOT.jar" ]