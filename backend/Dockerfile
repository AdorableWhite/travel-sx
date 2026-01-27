FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY common/pom.xml common/
COPY infrastructure/pom.xml infrastructure/
COPY service/pom.xml service/
COPY api/pom.xml api/
COPY common/src common/src
COPY infrastructure/src infrastructure/src
COPY service/src service/src
COPY api/src api/src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/api/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
