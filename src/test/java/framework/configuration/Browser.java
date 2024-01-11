package framework.configuration;

import org.openqa.selenium.WebDriver;

public class Browser {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public Browser(String browserType) {
        WebDriver driver = BrowserFactory.createBrowser(browserType);
        driverThreadLocal.set(driver);
    }

    public void navigateToUrl(String url) {
        getDriver().get(url);
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public void closeBrowser() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }
}
