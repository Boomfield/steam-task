package steam.pages.gamePage;

import framework.elements.Label;
import org.openqa.selenium.By;
import steam.pages.BasePage;
import steam.pages.components.HeaderComponent;

public class GamePage extends BasePage {
    public HeaderComponent headerComponent;
    private static By blkGameMediaBlock = By.className("game_background_glow");
    private Label lblGameName = new Label(By.id("appHubAppName"), "game name");

    public GamePage() {
        super(blkGameMediaBlock);
        this.headerComponent = new HeaderComponent();
    }

    public void verifyCurrentPageAndClickInstall(String gameName) {
        softAssert.assertTrue(lblGameName.getText().equalsIgnoreCase(gameName));
        headerComponent.clickInstallSteam();
    }
}
