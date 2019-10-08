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
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("http://192.168.1.15/admin/");
        webDriver.findElement(By.cssSelector("li"));
        int size = webDriver.findElements(By.cssSelector("ul#box-apps-menu>li")).size();
        for (int i = 0; i < size; i++) {
            webDriver.findElements(By.cssSelector("ul#box-apps-menu>li")).get(i).click();
            System.out.println(webDriver.findElement(By.tagName("title")).getAttribute("text"));
            System.out.println("Menu# " + (i + 1) + " :" + webDriver.findElements(By.cssSelector("ul.docs>li")).size());
        }
    }

    private void loginAdminPage() {
        webDriver.get("http://192.168.1.15/admin/login.php?redirect_url=%2Fadmin%2F");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
