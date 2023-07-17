
# Sample REST CRUD API with Spring Boot, Mysql, JPA and Hibernate

## Steps to Setup

**1. Clone the application**

```bash
https://github.com/givanthak/spring-boot-rest-api-tutorial.git
```

**2. Create Mysql database**
```bash
create database myStore
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/v1/products
    
    POST /api/v1/products
    
    GET /api/v1/products/{id}
    
    PUT /api/v1/products/{id}
    
    DELETE /api/v1/products/{id}

