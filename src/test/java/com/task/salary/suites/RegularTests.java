package com.task.salary.suites;

import com.task.salary.business.calculator.impl.MonthValueCalculatorTest;
import com.task.salary.rest.CountryControllerTest;
import com.task.salary.service.country.CountryServiceTest;
import com.task.salary.tools.net.nbp.impl.NbpAverageExchangeGetterTest;
import com.task.salary.tools.net.nbp.impl.NbpConnectorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({MonthValueCalculatorTest.class,
                     CountryControllerTest.class,
                     CountryServiceTest.class,
                     NbpAverageExchangeGetterTest.class,
                     NbpConnectorTest.class})
public class RegularTests {
}
