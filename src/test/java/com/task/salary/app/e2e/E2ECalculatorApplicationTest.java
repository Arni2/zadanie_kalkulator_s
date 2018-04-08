package com.task.salary.app.e2e;

import com.task.salary.app.CalculatorApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalculatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class E2ECalculatorApplicationTest {

    @LocalServerPort
    private int port;

    private static WebDriver driver;

    private int WAIT_SECONDS_FOR_WEBSITE = 5;

    @BeforeClass
    public static void setUp() {

        if(System.getProperty("webdriver.gecko.driver") == null) {
            System.setProperty("webdriver.gecko.driver", "c:\\programs\\gecko\\geckodriver.exe");
        }

        FirefoxOptions driverOptions = new FirefoxOptions();
        driverOptions.addArguments("--headless");
        driver = new FirefoxDriver(driverOptions);
    }

    @Test
    public void testGermanCurrencySelection() {
        driver.get("http://localhost:" + port);

        waitForWebsite();

        Select currencyCombo = new Select(driver.findElement(By.id("currencyComboId")));
        currencyCombo.selectByValue("GERMANY");
        WebElement selectedCurrency = driver.findElement(By.id("selectedCurrencyId"));
        assertEquals("German currency is EUR", "EUR", selectedCurrency.getText());
    }

    @Test
    public void testGermanExchangeMonthValue() {
        driver.get("http://localhost:" + port);

        waitForWebsite();

        Select currencyCombo = new Select(driver.findElement(By.id("currencyComboId")));
        currencyCombo.selectByValue("GERMANY");
        String moneyPerDayNetValue = "100";
        driver.findElement(By.id("inputNetValueId")).sendKeys(moneyPerDayNetValue);
        WebElement calculateAction = driver.findElement(By.id("actionCalculateMonthValueId"));
        calculateAction.sendKeys(Keys.ENTER);

        waitForWebsite();

        WebElement calculatedMonthValue = driver.findElement(By.id("calculatedMonthValueId"));

        assertEquals("Expected month value for 100 EUR per day is:", "3840.00", calculatedMonthValue.getText());
    }

    @Test
    public void testPolishExchangeMonthValue() {
        driver.get("http://localhost:" + port);

        waitForWebsite();

        Select currencyCombo = new Select(driver.findElement(By.id("currencyComboId")));
        currencyCombo.selectByValue("POLAND");
        String moneyPerDayNetValue = "200";
        driver.findElement(By.id("inputNetValueId")).sendKeys(moneyPerDayNetValue);
        WebElement calculateAction = driver.findElement(By.id("actionCalculateMonthValueId"));
        calculateAction.sendKeys(Keys.ENTER);

        waitForWebsite();

        WebElement calculatedMonthValue = driver.findElement(By.id("calculatedMonthValueId"));

        assertEquals("Expected month value for 200 PLN per day is:", "2364.00", calculatedMonthValue.getText());
    }

    @AfterClass
    public static void closeWebDriver() {
        driver.quit();
    }

    private void waitForWebsite() {
        try {
            TimeUnit.SECONDS.sleep(WAIT_SECONDS_FOR_WEBSITE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
