package framework.configuration;

import framework.helpers.PropertyHelper;
import org.openqa.selenium.WebDriver;

public class Browser {

    private static WebDriver driver;
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public static final String PATH_DOWNLOAD_PACKAGE = PropertyHelper.getDownloadPath();
    public static final int DEFAULT_PAGE_LOADED_TIMEOUT = PropertyHelper.getDefaultImplicitlyWait();

    public Browser(String browserType) {
        driver = BrowserFactory.createBrowser(browserType);
        setDriver();
    }

    public static void setDriver() {
        driverThreadLocal.set(driver);
    }

    public void navigateToUrl(String url) {
        driver.get(url);
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
