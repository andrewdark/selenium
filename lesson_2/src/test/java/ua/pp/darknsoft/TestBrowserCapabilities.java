package ua.pp.darknsoft;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBrowserCapabilities {
    private WebDriver driver;
    private WebDriverWait wait;
    private ChromeOptions options;

    @Before
    public void start() {
        options = new ChromeOptions();
        options.setCapability("unexpectedAlertBehaviour", "dismiss");
        driver = new ChromeDriver(options);
        System.out.println(((ChromeDriver) driver).getCapabilities());
        System.out.println(options);
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void searchOnGoogle() {
        driver.get("http://www.google.com");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
