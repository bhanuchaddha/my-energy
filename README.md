# My Energy Service

## Problem
Design a service to support features mentioned in the requirement [document](Tech%20Test%20for%20SSE.docx).

## API Document
API document can found at [this](https://documenter.getpostman.com/view/3772012/SzKSUfzj?version=latest) url.

## Features

### Create Price Plans
One can create Price Plans using this Service. This is internal url and should be accessed only by Admin. 
This is put under `/internal` root so that different Authentication/Authorization can be applied to it.

**URL:** http://localhost:8080/myenergy/internal/price-plan

```json
POST /myenergy/internal/price-plan HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
	"planName": "price-plan-2",
	"companyName":"Power Energy",
	"unitRate":1
}  
```

### Enroll Smart Meter to a Price Plan
One can enroll their Smart Meter to specific plan using this url. This is internal url and should be accessed only by Admin. 
This is put under `/internal` root so that different Authorization can be applied to it.

**URL:** http://loclahost:8080/myenergy/internal/smart-meter

```json
POST /myenergy/internal/smart-meter HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
	"customerName": "Alex",
	"smartMeterId":"smart-meter-4",
	"pricePlanId":"price-plan-1"
}       
```

### Store Reading
This API can be used by Smart Meters to record/store the reading.

**URL:** http://localhost:8080/myenergy/readings/store

```json
POST /myenergy/readings/store HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
    "smartMeterId": "smart-meter-0",
    "electricityReadings": [
        { "time": 1579613461, "reading": 1.32 }
    ]
}     
```
### Read Reading
This API can be used to view the reading available for a given meter.

**URL:** http://localhost:8080/myenergy/readings/read/{smartMeterId}

####**Response**
```json
[
    {
        "time": "2020-01-01T09:15:30Z",
        "reading": 1.32
    },
    {
        "time": "2020-02-01T09:15:30Z",
        "reading": 2.32
    },
    {
        "time": "2020-03-01T09:15:30Z",
        "reading": 4.27
    },
    {
        "time": "2020-04-01T08:15:30Z",
        "reading": 10.3
    }
]    
```

### Usage Cost Comparison
This API can be used to get the comparison of usage cost with each Price Plan.

**URL:** http://localhost:8080/myenergy/price-plans/compare-all/{smartMeterId}

####**Response**
```json
{
    "pricePlanComparisons": {
        "price-plan-2": 10.3,
        "price-plan-1": 20.6,
        "price-plan-0": 103.0
    },
    "pricePlanId": "smart-meter-0"
}  
```

### Price plan recommendation
This API can be used to get the Price Plan recommendation. The first item in the list is recommended plan.

**URL:** http://localhost:8080/myenergy/price-plans/recommend/{smartMeterId}

####**Response**

```json
[
    {
        "price-plan-2": 10.3
    },
    {
        "price-plan-1": 20.6
    },
    {
        "price-plan-0": 103.0
    }
]
```

## Technical Features
* Easily navigable service package structure.
* Separation of concern using 3 layer architecture (Resource, Service, Persistence)
* Using separate models for Presentation(DTO) and Internal(entity) usage.
* Centralized Exception handling using `ApplicationExceptionHandler`.
* Structured Error response for all exceptions along with correct Http status code.
* Centralized understandable error codes. `ErrorCode`.
* API testing with `RestAssured`.
* Builder Pattern is used to create immutable objects. Setters are used where ever necessary like for JPA and Marshaling/Unmarshaling.
* Logging
* Validations
* Separate controller of internal/admin endpoints.
* Separate Service is used for each used-case to enable Single Responsibility Principle.
* Each Service has its own well defined input and output contacts to implement Encapsulation.

## Further Improvements
* Java Docs
* Recommendation endpoint could just return one Price Plan.
* MapStruct could be used for mappers.
* Null checks and mandatory value validation.


## Technology Used
* Spring Boot
* Java 8 - streams
* JPA
* Lombok
* H2 in-memory db
* RestAssured 


## Unit Testing
As there is not much logic in the service. I have decided to use API testing. API test can be found in test package.

Command `mvn clean install` would have failed test because All api tests required running service instance. Thus first start the service and then run the tests using below
```json
mvn test
```

## Running the application
Execute below command to start the application.
```json
mvn spring-boot:run
```
Service would be started on localhost:8080. Different APIs of the service could be tested directly from Postman or Terminal using  [API document](https://documenter.getpostman.com/view/3772012/SzKSUfzj?version=latest).

### Data Population
To check the recommendation APIs, there must be data in the database. For this `GenerateTestData` utility can be used. 

Run below command in terminal after application has been started.
```json
mvn clean test -Dtest=com.bhanuchaddha.myenergy.GenerateTestData
```

























 