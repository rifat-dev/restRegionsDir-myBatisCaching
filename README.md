# Spring Boot CRUD app with MyBatis for data access (H2 DB) and Spring Cache

### There are two useful files in repository:
* **./test-data.json**              (test data)
* **./openApi-pretty-json.json**    (API)

### URL for H2 console:
```localhost:8080/h2-console``` Access to H2 (user: user; password: user)

## Install and run
**Requirements**: JDK, Maven, Git
1. **Clone repository:**
```
git clone git@github.com:rifat-dev/restRegionsDir-myBatisCaching.git
```

2. **Go to project dir:**
```
cd restRegionsDir-myBatisCaching
```

3. **Run app by maven:**
```
mvn spring-boot:run
```

## Structure of ./src
```
./src
├── main
│   ├── java
│   │   └── com
│   │       └── directory
│   │           └── regions
│   │               ├── Config.java
│   │               ├── RegionsApplication.java
│   │               ├── controller
│   │               │   └── RegionController.java
│   │               ├── model
│   │               │   ├── InRegionDTO.java
│   │               │   └── Region.java
│   │               ├── repository
│   │               │   └── RegionRepository.java
│   │               ├── service
│   │               │   └── RegionService.java
│   │               └── typeHandler
│   │                   └── UuidTypeHandler.java
│   └── resources
│       ├── application.yml
│       └── schema.sql
└── test (Empty)
```

May 2024