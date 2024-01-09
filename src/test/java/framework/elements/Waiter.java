package framework.elements;

import framework.configuration.Browser;
import framework.helpers.PropertyHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.time.Duration;

public class Waiter {
    private static final int DEFAULT_EXPLICITLY_WAIT = PropertyHelper.getDefaultExplicitlyWait();
    private static final int DEFAULT_DOWNLOADING_WAIT = 60;
    private WebDriverWait wait;

    public boolean isElementPresent(By locator) {
        wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(DEFAULT_EXPLICITLY_WAIT));
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement waitElementIsClickable(By element) {
        wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(DEFAULT_EXPLICITLY_WAIT));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitForFileToDownload(String fileName) {
        File file = new File(Browser.PATH_DOWNLOAD_PACKAGE + fileName);

        FluentWait<File> wait = new FluentWait<>(file)
                .withTimeout(Duration.ofSeconds(DEFAULT_DOWNLOADING_WAIT))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchFileException.class);

        try {
            wait.until(localFile -> {
                long initialSize = localFile.length();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return localFile.length() == initialSize;
            });
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}

