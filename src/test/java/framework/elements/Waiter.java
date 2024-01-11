package framework.elements;

import framework.Logger;
import framework.configuration.Browser;
import framework.configuration.BrowserFactory;
import steam.helpers.PropertyConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.time.Duration;

public class Waiter {
    private static final int DEFAULT_ELEMENT_LOADED_TIMEOUT = PropertyConfig.getDefaultExplicitlyWait();
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

    public static boolean waitForFileToDownload(String fileName, int downloadTime) {
        File file = new File(BrowserFactory.PATH_DOWNLOAD_PACKAGE + fileName);
        FluentWait<File> wait = new FluentWait<>(file)
                .withTimeout(Duration.ofSeconds(downloadTime))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchFileException.class);

        try {
            wait.until(localFile -> localFile.exists() && localFile.length() > 0);
            Logger.logInfo(fileName + " downloaded");
            return true;
        } catch (TimeoutException e) {
            Logger.logFatal(fileName + " doesn't exist");
            return false;
        }
    }
}

