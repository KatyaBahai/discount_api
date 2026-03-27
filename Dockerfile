FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build
COPY pom.xml .
RUN mvn -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -DskipTests -Dcheckstyle.skip=true package

FROM maven:3.9.9-eclipse-temurin-21

WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar

COPY entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]