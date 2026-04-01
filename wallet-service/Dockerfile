FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy the entire service directory
COPY . .

# Build the application
RUN mvn -pl wallet-service -am clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=0 /app/wallet-service/target/*.jar app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "/app/app.jar"]