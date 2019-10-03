package ua.pp.darknsoft;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestGoogleSearch {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 50);
    }

    @Test
    public void searchOnGoogle() {
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("WebDriver");
        //if the tooltip closes the search button. For Firefox.
        //Actions action = new Actions(driver);
        //action.sendKeys(Keys.ESCAPE).perform();
        driver.findElement(By.name("btnK")).click();
        wait.until(ExpectedConditions.titleIs("WebDriver - Пошук Google"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;

    }
}
