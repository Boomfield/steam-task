package steam.pages.aboutPage;

import framework.elements.Button;
import org.openqa.selenium.By;
import steam.pages.BasePage;

public class AboutPage extends BasePage {
    public static final String STEAM_FILE_NAME = "SteamSetup.exe";
    private Button btnDownloadSteam = new Button(By.className("about_install_steam_link"), "download steam");
    private static By lblMainBlock = By.id("about_header_area");
    public AboutPage() {
        super(lblMainBlock);
    }

    public void downloadSteam () {
        btnDownloadSteam.clickElementWithAction();
        softAssert.assertTrue(waiter.waitForFileToDownload(AboutPage.STEAM_FILE_NAME));
        softAssert.assertAll();
    }
}
