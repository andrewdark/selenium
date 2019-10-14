package darknsoft.pp.ua;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class TestStickers {
    private WebDriver webDriver;

    @Before
    public void start() {
        webDriver = new ChromeDriver();
    }

    @Test
    public void testSticker() {
        webDriver.get("http://192.168.1.15/");
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<WebElement> products = webDriver.findElements(By.cssSelector("ul.listing-wrapper.products>li.product.column.shadow.hover-light"));

        assertTrue(products.size() > 0);
        for (WebElement element : products) {
            assertTrue(isElementPresent(element, By.className("sticker")));
        }
    }

    @After
    public void stop() {
        webDriver.quit();
        webDriver = null;
    }

    private boolean isElementPresent(WebElement element, By locator) {
        try {
            element.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

}
