package steam.pages;

import framework.elements.Label;
import org.openqa.selenium.By;

public class GamePage extends BaseStreamPage {
    private static By blkGameMediaBlock = By.className("game_background_glow");
    private Label lblGameName = new Label(By.id("appHubAppName"), "game name");

    public GamePage() {
        super(blkGameMediaBlock, "Game page");
    }

    public void verifyCurrentPageAndClickInstall(String gameName) {
        softAssert.assertTrue(lblGameName.getText().equalsIgnoreCase(gameName));
        headerComponent.clickInstallSteam();
    }
}
