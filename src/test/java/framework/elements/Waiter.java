package framework.elements;

import framework.configuration.Browser;
import org.openqa.selenium.support.ui.ExpectedCondition;
import steam.helpers.PropertyConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {
    private static final int DEFAULT_ELEMENT_LOADED_TIMEOUT = PropertyConfig.getDefaultExplicitlyWait();
    private static final int DEFAULT_FILE_DOWNLOAD_TIMEOUT = PropertyConfig.getDefaultFileDownloadTimeOut();

    private WebDriverWait wait;

    public boolean waitForElementPresent(By locator) {
        wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(DEFAULT_ELEMENT_LOADED_TIMEOUT));
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement waitElementIsClickable(By element) {
        wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(DEFAULT_ELEMENT_LOADED_TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForPageToLoad(JavascriptExecutor jsExecutor) {
        wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(DEFAULT_ELEMENT_LOADED_TIMEOUT));
        wait.until((WebDriver driver) -> jsExecutor.executeScript("return document.readyState").equals("complete"));
    }

    public boolean waitForConditionWithFluentWait(ExpectedCondition<Boolean> condition) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(Browser.getDriver())
                    .withTimeout(Duration.ofSeconds(DEFAULT_FILE_DOWNLOAD_TIMEOUT))
                    .pollingEvery(Duration.ofMillis(2000))
                    .ignoring(Exception.class);

            return wait.until(condition);
        } catch (TimeoutException e) {
            return false;
        }
    }
}

