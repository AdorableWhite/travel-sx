FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
# Configure Aliyun Maven Mirror for faster downloads in China
RUN mkdir -p /root/.m2 && \
    echo '<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" \
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" \
    xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd"> \
    <mirrors> \
        <mirror> \
            <id>aliyunmaven</id> \
            <mirrorOf>*</mirrorOf> \
            <name>Aliyun Public Mirror</name> \
            <url>https://maven.aliyun.com/repository/public</url> \
        </mirror> \
    </mirrors> \
    </settings>' > /root/.m2/settings.xml

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

# Changed from eclipse-temurin:17-jre-alpine to openjdk:17-jdk-slim for better multi-platform support
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/api/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
