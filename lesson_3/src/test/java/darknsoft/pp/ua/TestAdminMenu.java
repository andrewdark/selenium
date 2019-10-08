package darknsoft.pp.ua;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

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
            System.out.println(webDriver.findElement(By.tagName("title")).getAttribute("text"));
            int innerSize = webDriver.findElements(By.cssSelector("ul.docs>li")).size();
            System.out.println("Menu# " + (i + 1) + " :" + innerSize);
            if (innerSize > 0) {
                for (int j = 0; j < innerSize; j++) {
                    webDriver.findElements(By.cssSelector("ul.docs>li")).get(j).click();
                    System.out.println((j + 1) + ") " + (webDriver.findElement(By.tagName("title")).getAttribute("text")).trim());
                }
            }
        }
    }

    private void loginAdminPage() {
        webDriver.get("http://192.168.1.15/admin/login.php?redirect_url=%2Fadmin%2F");
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        webDriver.findElement(By.cssSelector("input[name=username]")).sendKeys("admin");
        webDriver.findElement(By.cssSelector("input[name=password]")).sendKeys("admin");
        webDriver.findElement(By.cssSelector("button[name=login]")).click();
    }

    @After
    public void stop() {
        webDriver.quit();
        webDriver = null;
    }

}
