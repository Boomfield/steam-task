package framework.downloadPages;

import framework.configuration.Browser;
import org.openqa.selenium.JavascriptExecutor;

public class FirefoxDownloadPage extends BaseDownloadPage {
    @Override
    public boolean isDownloadComplete() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Browser.getDriver();
        String valueAttribute = (String) jsExecutor.executeScript(
                "return document.querySelector('progress.downloadProgress').getAttribute('value');");

        return "100".equals(valueAttribute);
    }

    @Override
    public String getDownloadUrl() {
        return "about:downloads";
    }
}
