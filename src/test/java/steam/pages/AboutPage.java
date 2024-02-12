package steam.pages;

import framework.configuration.BrowserFactory;
import framework.downloadPages.DownloadPageFactory;
import framework.elements.Button;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class AboutPage extends BaseSteamPage {
    public static final String STEAM_FILE_NAME = "SteamSetup.exe";
    private Button btnDownloadSteam = new Button(By.className("about_install_steam_link"), "download steam");
    private static By lblMainBlock = By.id("about_header_area");

    public AboutPage() {
        super(lblMainBlock, "About page");
    }

    @Step("Click on download steam and wait to download")
    public void downloadSteam() {
        btnDownloadSteam.clickElementWithAction();
        softAssert.assertTrue(DownloadPageFactory.getDownloadPageObject(BrowserFactory.BROWSER_TYPE).verifyFileDownloaded(STEAM_FILE_NAME));
        softAssert.assertAll();
    }
}
