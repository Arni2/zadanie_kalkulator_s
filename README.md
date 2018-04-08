# zadanie_kalkulator_s
Month salary calculator 

# Used services
- using NPB API to download average currency exchange to PLN

# Action
- calculates month earnings after taxes and costs according to net day earnings

# Tests
- regular tests:
mvn -Dtest=com.task.salary.suites.RegularTests test
- e2e tests, need to set system variable to Gecko driver:
mvn -Dtest=com.task.salary.suites.E2ETests -Dwebdriver.gecko.driver=c:\programs\gecko\geckodriver.exe test
- internet connecting test
mvn -Dtest=com.task.salary.suites.NetIsUsedTests test