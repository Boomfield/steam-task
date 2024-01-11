package steam.pages;

import framework.elements.Button;
import framework.elements.Waiter;
import org.openqa.selenium.By;

public class AboutPage extends BasePage {
    private static final int DEFAULT_DOWNLOADING_WAIT = 60;
    public static final String STEAM_FILE_NAME = "SteamSetup.exe";
    private Button btnDownloadSteam = new Button(By.className("about_install_steam_link"), "download steam");
    private static By lblMainBlock = By.id("about_header_area");

    public AboutPage() {
        super(lblMainBlock, "About page");
    }

    public void downloadSteam() {
        btnDownloadSteam.clickElementWithAction();
        softAssert.assertTrue(Waiter.waitForFileToDownload(AboutPage.STEAM_FILE_NAME, DEFAULT_DOWNLOADING_WAIT));
        softAssert.assertAll();
    }
}
