package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriverSupport.DriverManager;

import java.time.Duration;
import java.util.List;

public class BaseClass {

    public WebDriver driver;
    private static final long TIMEOUT = 30;

    public BaseClass() {

        this.driver = DriverManager.getDriver();
    }

    public WebElement getElement(By locator) {
        waitForElementVisibility(locator);
        return driver.findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        waitForElementVisibility(locator);
        return driver.findElements(locator);
    }

    public String getText(By locator) {
        WebElement element = getElement(locator);
        return element.getText();
    }

    public void clickOnElement(By locator) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", getElement(locator));
    }

    public void clickOnElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickElement(By locator) {
       getElement(locator).click();
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void waitForElementVisibility(By locator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public void launchURL(String URL) {
        driver.get(URL);
    }
}
