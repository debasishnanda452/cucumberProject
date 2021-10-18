package webdriverSupport;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverManager {
    private static final long TIMEOUT = 5;
    static ThreadLocal<WebDriver> DRIVER = new ThreadLocal<WebDriver>() {

        protected WebDriver initialValue() {
            return getChromeDriver();
        }
    };

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().clearDriverCache();
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    public static void removeDriver() {
        DRIVER.get().quit();
        DRIVER.remove();
    }

}
