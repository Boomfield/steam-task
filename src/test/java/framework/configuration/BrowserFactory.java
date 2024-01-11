package framework.configuration;

import steam.helpers.PropertyConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;

public class BrowserFactory {
    public static final String PATH_DOWNLOAD_PACKAGE = PropertyConfig.getDownloadPath();
    private static final int DEFAULT_PAGE_LOADED_TIMEOUT = PropertyConfig.getDefaultImplicitlyWait();

    public static WebDriver createBrowser(String browserType) {
        WebDriver driver;
        switch (browserType.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = getChromeOptions();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(DEFAULT_PAGE_LOADED_TIMEOUT));
        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", false);
        prefs.put("safebrowsing.enabled", true);
        prefs.put("download.default_directory", PATH_DOWNLOAD_PACKAGE);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("disable-blink-features=BlockCredentialedSubresources");
        return chromeOptions;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", PATH_DOWNLOAD_PACKAGE);
        options.addPreference("privacy.resistFingerprinting", true);
        return options;
    }
}
