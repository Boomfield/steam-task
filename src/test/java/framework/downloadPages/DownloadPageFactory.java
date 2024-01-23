package framework.downloadPages;

public class DownloadPageFactory {

    public static BaseDownloadPage getDownloadPageObject(String browserType) {
        switch (browserType) {
            case ("chrome"):
                return new ChromeDownLoadPage();
            case ("firefox"):
                return new FirefoxDownloadPage();
            default:
                throw new IllegalArgumentException("Unsupported browser type");
        }
    }
}