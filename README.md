# Backend Scooter App

A backend application for a scooter management system based on microservices, built with Spring Boot and Spring Cloud Gateway.

## Architecture

The project follows a microservices architecture with the following components:

### Services
- **Gateway Service** (port 8080): Main entry point that exposes all services externally
- **Auth Service** (port 8091): Authentication and authorization management
- **User Service** (port 8082): User management
- **Scooter Service** (port 8081): Scooter management
- **Trip Service** (port 8085): Trip management
- **Account Service** (port 8083): Account management
- **Wallet Service** (port 8084): Digital wallet management
- **Support Service** (port 8086): Support service
- **Chat Bot Service** (port 8090): AI chatbot 

### Databases
- **MySQL** (port 3306) 
- **MongoDB** (port 27017) 

## Technologies

- **Java 21**
- **Spring Boot 3.3.5**
- **Spring Cloud 2023.0.3**
- **Spring Cloud Gateway**
- **MySQL 8**
- **MongoDB 6**
- **Docker & Docker Compose**
- **Maven**

## Prerequisites

- Docker and Docker Compose installed
- Java 21 (for local development)
- Maven 3.6+ (for local development)

## Volume Configuration (MANDATORY STEP)

Before starting the application, you must create the Docker volumes manually:

```bash
docker volume create backend-scooter-app_mysql_data
docker volume create backend-scooter-app_mongo_data
```

**Important**: These volumes are configured as `external: true` in the docker-compose.yml, so they must be created manually before starting the containers.

## Running the Project

### 1. Create the volumes (if they don't exist)

```bash
docker volume create backend-scooter-app_mysql_data
docker volume create backend-scooter-app_mongo_data
```

### 2. Configure environment variables

Create a `.env` file in the project root with:

```env
GROQ_API_KEY=your_api_key_here
```

### 3. Start the application with Docker Compose

```bash
docker-compose up --build
```

Or to run in the background:

```bash
docker-compose up -d --build
```

### 4. Verify service status

You can verify that all services are running with:

```bash
docker-compose ps
```

## Accessing Services

- **Main Gateway**: http://localhost:8080
- **API Documentation**: Each service exposes its own OpenAPI/Swagger documentation through the gateway

## Local Development

To run a specific service locally:

```bash
cd [service-name]
mvn spring-boot:run
```

## Project Structure

```
backend-scooter-app/
├── account-service/         # Account management
├── auth-service/            # Authentication
├── chat-bot-service/        # AI chatbot
├── gateway-service/         # API Gateway
├── scooter-service/         # Scooter management
├── support-service/         # Technical support
├── trip-service/            # Trip management
├── user-service/            # User management
├── wallet-service/          # Digital wallets
├── docker-compose.yml       # Container configuration
└── pom.xml                  # Main Maven configuration
```

## Useful Commands

### Start the application
```bash
docker-compose start
```

### Stop the application
```bash
docker-compose down
```

### View logs of a specific service
```bash
docker-compose logs [service-name]
```

### Rebuild and start the application
```bash
docker-compose up --build --force-recreate
```

### Clean up volumes (use with caution)
```bash
docker-compose down -v
docker volume rm backend-scooter-app_mysql_data backend-scooter-app_mongo_data
```

## Important Notes

1. **External Volumes**: MySQL and MongoDB volumes must be created manually before starting the application
2. **External Port**: Only the gateway-service (port 8080) is exposed externally. Other services are only accessible through the gateway
3. **API Key Required**: The chat-bot-service requires a valid Groq API key to function properly
4. **Internal Network**: All services communicate through an internal Docker network called "internal"


## Troubleshooting

### Common issues:

1. **Volume not found error**: Make sure to create the volumes manually before starting
2. **Database connection error**: Verify that MySQL and MongoDB containers are running correctly
3. **API Key error**: Configure the GROQ_API_KEY variable correctly in the .env file

### Check container status:
```bash
docker-compose ps
docker-compose logs
```

## Contribution

The project follows a standard Spring Boot microservices structure. Each service is independent and can be developed and deployed separately.
