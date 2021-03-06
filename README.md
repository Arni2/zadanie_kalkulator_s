# zadanie_kalkulator_s
Web application for caclulating Month Salary income in given country 

# About
calculates month earnings after taxes and costs according to net day earnings in given country

# Service
in browser enter:
```
http://localhost:8080 
```
to use service

# Used external services
using NPB API to download average currency exchange to PLN

# Used technologies
- Spring Boot 2.0
- H2 - database
- Liquibase - for versioning of database
- Selenium - for E2E tests
- JUnit - for tests
- Gson - for JSON ORM
- Angular 1.6.9 - frontend technology

# Required
- Java 8 SDK
- Maven 3.5

# How to run
run on port 8080
```
mvn spring-boot:run
```

# Database
application uses H2 database

# Spring Profiles
- dev - for development
- test - for tests with Mocks
- net_test - for tests with connection with NBP real API

# Tests
- regular tests:<br/>
```
mvn -Dtest=com.task.salary.suites.RegularTests test
```
- internet connecting test - connects with NBP API<br/>
```
mvn -Dtest=com.task.salary.suites.NetIsUsedTests test
```

# Gecko driver and Firefox
- download Gecko driver from https://github.com/mozilla/geckodriver/releases
- download Firefox browser from https://www.mozilla.org/pl/firefox/new/
# E2E Tests
e2e tests, need to set system variable to Gecko driver:<br/>
```
mvn -Dtest=com.task.salary.suites.E2ETests -Dwebdriver.gecko.driver=c:\programs\gecko\geckodriver.exe test
```
