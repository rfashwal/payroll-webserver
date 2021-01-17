# Payroll Webserver

A Kotlin Spring Boot web application that includes functionalities.

### Small Logic Tasks
Included in the same project with standalone controller:
#### Endpoints:
- GET /api/logiceee/starrings/{value}
a recursive method where identical chars that are adjacent in the original string will be separated by a "*".

- GET /api/logiceee/verify/{email}
a method that accepts a string and checks if this is a valid email address

### Implementation

#### Database
I used Spring Data JPA, which is part of the larger Spring Data family, makes it easy to easily implement JPA based repositories. This module deals with enhanced support for JPA based data access layers. It makes it easier to build Spring-powered applications that use data access technologies.

#### Web 
Spring Boot offers a fast way to build applications. It looks at your classpath and at the beans you have configured, makes reasonable assumptions about what you are missing, and adds those items. With Spring Boot, you can focus more on business features and less on infrastructure.

#### Payroll Calculator
An interface that contains a calculate method and has two concrete implementations for Fixed and Monthly Members

![CalculatorDiagram](https://github.com/rfashwal/payroll-webserver/blob/main/images/calculatorDiagram.png)

#### Main Entities 
![Entities](https://github.com/rfashwal/payroll-webserver/blob/main/images/entities.png)

#### Endpoints
  
You can access Swagger UI to list and test Payroll endpoints
Run the app and access via: http://localhost:8080/swagger-ui.html

##### Three main APIs

- Staff Members
    - GET /api/members: Get list of all members
    - POST /api/members: create new Staff Member
    - GET /api/members/sorted: Get list of all members sorted using Bubble Sort
    - DELETE /api/members/{id}: delete member object by Id
    - GET /api/members/{id}: Get staff member by Id
    - PATCH /api/members/{id}: Update member object by Id
- Payroll
    - POST /api/payroll/worklog: create new worklog
    - GET /api/payroll/{memberId}: generate payroll for a staff member in a given period
    - GET /api/payroll/{memberId}/export: generate payroll document for a staff member in a given period

- Logic Tasks(not related to payroll)
    - GET /api/logiceee/starrings/{value}: a recursive method where identical chars that are adjacent in the original string will be separated by a "*".
    - GET /api/logiceee/verify/{email}:a method that accepts a string and checks if this is a valid email address

![swagger](https://github.com/rfashwal/payroll-webserver/blob/main/images/swagger.png)

#### Build 
Used maven as package manager

```
mvn test
mvn install
```
## :whale: Containerization
A dockerfile is created to build and dockerise the payroll server.

An image is already built and cun be pulled:
```
docker pull rfashwal/payroll-webserver
```

To run locally using docker:
```
docker run -p 8080:8080 rfashwal/payroll-webserver
```

## External Libraries: 
* Spring Web
* Mustache
* Spring Data JPA
* H2 Database
* Spring Boot DevTools
