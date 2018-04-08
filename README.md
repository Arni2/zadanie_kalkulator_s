# zadanie_kalkulator_s
Month salary calculator 

# What is doing
- calculates month earnings after taxes and costs according to net day earnings

# Used services
- using NPB API to download average currency exchange to PLN

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
