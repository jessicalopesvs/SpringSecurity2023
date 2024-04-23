# Spring Security 6 Java Application with REST API

Welcome to our Java application utilizing the new Spring Security 6, released in 2023. This project focuses on user authentication and authorization using Spring Security to provide secure access to a REST API. The application includes management of users, roles, and privileges.

## Getting Started

To get started with this project, follow these steps:

1. **Clone the repository:**
   ```bash 
   git clone https://github.com/jessicalopesvs/SpringSecurity2023 
   ```


2. **Build the project:**
   ```bash 
    cd SpringSecurity2023 
    mvn clean install
   ```


3. **Run the application:**

```arduino 
    mvn spring-boot:run
   ```

## Prerequisites

Make sure you have the following installed:

- Java 8 or higher
- Maven
- Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)

## Configuration

1. **Database Setup:**
- This project uses MySQL as the database. Configure your MySQL connection settings in `application.properties`.

2. **Spring Security Configuration:**
- Spring Security configuration is handled in `SecurityConfig.java`. You can customize security settings according to your requirements.

3. **REST API Endpoints:**
- API endpoints are defined in `MainController.java` and protected by Spring Security. You can define new endpoints or modify existing ones as needed.

## Usage

Once the application is running, you can interact with the REST API endpoints. Here are some example requests:

- **Register a new user:**
```arduino 
POST /register
{
 "username": "user",
 "password": "password"
}
```


- **Authenticate and get JWT token:**
```arduino 
POST /api/authenticate
{
"username": "user",
"password": "password"
}
```



## Dependencies

- Spring Boot Starter Security
- Spring Boot Starter Web
- Spring Boot DevTools (runtime)
- Lombok (optional)
- Spring Boot Starter Test (test)
- Spring Security Test (test)
- Spring Boot Starter Data JDBC
- Spring Boot Starter Data JPA
- MySQL Connector Java 8.0.26
- Spring Boot Starter Thymeleaf (optional)


---

**Note:** This project is a demonstration of integrating Spring Security 6 with a Java application for secure REST API access. Ensure proper security practices are followed when deploying in production environments.




