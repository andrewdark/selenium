package darknsoft.pp.ua;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class TestAdminMenu {
    private WebDriver webDriver;

    @Before
    public void start() {
        webDriver = new ChromeDriver();
    }

    @Test
    public void testAdminMenu() {
        loginAdminPage();
        webDriver.get("http://192.168.1.15/admin/");
        int outerSize = webDriver.findElements(By.cssSelector("ul#box-apps-menu>li")).size();
        for (int i = 0; i < outerSize; i++) {
            webDriver.findElements(By.cssSelector("ul#box-apps-menu>li")).get(i).click();
            assertTrue(isElementPresent(webDriver, By.tagName("title")));
            int innerSize = webDriver.findElements(By.cssSelector("ul.docs>li")).size();
            if (innerSize > 0) {
                for (int j = 0; j < innerSize; j++) {
                    webDriver.findElements(By.cssSelector("ul.docs>li")).get(j).click();
                    assertTrue(isElementPresent(webDriver, By.tagName("title")));
                }
            }
        }
    }

    @After
    public void stop() {
        webDriver.quit();
        webDriver = null;
    }
    
    private boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    private void loginAdminPage() {
        webDriver.get("http://192.168.1.15/admin/login.php");
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
        webDriver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin");
        webDriver.findElement(By.cssSelector("button[name=login]")).click();
    }
}
