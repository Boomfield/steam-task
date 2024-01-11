package steam.tests;

import framework.configuration.Browser;
import steam.helpers.PropertyConfig;
import steam.helpers.PropertyLocalization;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import steam.pages.helpers.Language;

public class BaseTest {
    protected Browser browser;
    protected static Language languageName;

    @BeforeSuite
    public void onStart() {
        PropertyConfig.loadProperties("src/test/resources/config.properties");
        String language = System.getProperty("language", "en");
        languageName = Language.getByCodeName(language);
        PropertyLocalization.loadProperties(String.format("src/test/resources/localization/loc-%s.properties", language));
    }

    @BeforeMethod
    public void setUp() {
        browser = new Browser(PropertyConfig.getBrowser());
        browser.navigateToUrl(PropertyConfig.getUrl());
    }

    @AfterMethod
    public void tearDown() {
        browser.closeBrowser();
    }
}