package Browser;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Browser {

    private static WebDriver driver;

    public static void browserInit() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sherfe.metkova\\Desktop\\tech\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebDriver driver() {
        return driver;
    }

    public static void open(String url) {
        driver.get(url);
    }

    public void isOpened(String url) throws Error {
        Assert.assertEquals("Page is not loaded", url, driver.getCurrentUrl());
    }

    public static void quit() {
        driver.quit();
    }
}
