package framework.downloadPages;

import framework.configuration.Browser;
import framework.configuration.BrowserFactory;
import framework.elements.Waiter;
import framework.helpers.FileHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public abstract class BaseDownloadPage {
    private Waiter waiter;

    public BaseDownloadPage() {
        this.waiter = new Waiter();
    }

    public boolean verifyFileDownloaded(String fileName) {
        WebDriver driver = Browser.getDriver();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(getDownloadUrl());
        boolean downloadComplete = waiter.waitForConditionWithFluentWait(x -> isDownloadComplete());
        return downloadComplete && FileHelper.isFileExists(BrowserFactory.CANONICAL_PATH_DOWNLOAD_PACKAGE, fileName);
    }

    public abstract boolean isDownloadComplete();

    public abstract String getDownloadUrl();
}
