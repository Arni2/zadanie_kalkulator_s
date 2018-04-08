# zadanie_kalkulator_s
Web application for caclulating Month Salary income in given country 

# What is doing
- calculates month earnings after taxes and costs according to net day earnings in given country

# Used services
- using NPB API to download average currency exchange to PLN

# Required
- Java 8 SDK
- Maven 3.5

# How to run
- run
```
mvn spring-boot:run
```

# Database
- application uses H2 database

# Spring Profiles
- dev - for development
- test - for tests with Mocks
- net_test - for tests with connection with NBP real API

# Tests
- regular tests:<br/>
```
mvn -Dtest=com.task.salary.suites.RegularTests test
```
- e2e tests, need to set system variable to Gecko driver:<br/>
```
mvn -Dtest=com.task.salary.suites.E2ETests -Dwebdriver.gecko.driver=c:\programs\gecko\geckodriver.exe test
```
- internet connecting test<br/>
```
mvn -Dtest=com.task.salary.suites.NetIsUsedTests test
```
