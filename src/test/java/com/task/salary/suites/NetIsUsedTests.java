package com.task.salary.suites;

import com.task.salary.app.net.NetNbpAverageExchangeGetterTest;
import com.task.salary.app.net.NetNbpConnectionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({NetNbpAverageExchangeGetterTest.class,
                     NetNbpConnectionTest.class})
public class NetIsUsedTests {
}
