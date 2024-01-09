package tests;

import framework.configuration.Browser;
import framework.configuration.BrowserFactory;
import framework.helpers.PropertyHelper;
import framework.helpers.PropertyLocalizationHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static framework.configuration.Browser.getDriver;

public class BaseTest {
    protected Browser browser;

    @BeforeSuite
    public void onStart() {
        PropertyHelper.loadProperties("src/test/resources/config.properties");
        String language = System.getProperty("language", "en");
        PropertyLocalizationHelper.loadProperties(String.format("src/test/resources/localization/loc-%s.properties", language));
    }

    @BeforeMethod
    public void setUp() {
        browser = new Browser(PropertyHelper.getBrowser());
        browser.navigateToUrl(PropertyHelper.getUrl());
    }

    @AfterMethod
    public void tearDown() {
            browser.closeBrowser();
    }
}