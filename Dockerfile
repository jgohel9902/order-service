# ================================
# 1. Build Stage
# ================================
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Cache dependencies
COPY pom.xml .
RUN mvn -q dependency:go-offline

# Copy all source
COPY src ./src

# Build application
RUN mvn -q clean package -DskipTests

# ================================
# 2. Run Stage
# ================================
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy built jar
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]
