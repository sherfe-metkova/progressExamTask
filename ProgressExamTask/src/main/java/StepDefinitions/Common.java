package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Common {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sherfe.metkova\\Desktop\\tech\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        return driver;

    }

    public static WebDriver driver() {
        return driver;
    }

    public static void open(String url) {
        driver.get(url);
    }

    public static void quit() {
        driver.quit();
    }

    public String generateEmail() {
        String result = "";
        Random random = new Random();
        result = "user" + random.nextInt() + "@yopmail.com";
        return result;
    }
}

