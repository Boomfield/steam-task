package framework.downloadPages;

import framework.configuration.Browser;
import org.openqa.selenium.JavascriptExecutor;

public class ChromeDownLoadPage extends BaseDownloadPage {
    @Override
    public boolean isDownloadComplete() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Browser.getDriver();
        return (boolean) jsExecutor.executeScript(
                "const items = document.querySelector('downloads-manager').shadowRoot.querySelectorAll('#downloadsList downloads-item');" +
                        "if (items.length > 0) {" +
                        "  const lastItem = items[items.length - 1];" +
                        "  const progress = lastItem.shadowRoot.querySelector('#progress');" +
                        "  return (progress.hasAttribute('value') && progress.getAttribute('value') == '100');" +
                        "} else {" +
                        "  return false;" +
                        "}");
    }

    @Override
    public String getDownloadUrl() {
        return "chrome://downloads";
    }
}
