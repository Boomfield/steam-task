package steam.tests;

import framework.Logger;
import framework.configuration.BrowserFactory;
import steam.helpers.PropertyLocalization;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steam.pages.AboutPage;
import steam.pages.ActionPage;
import steam.pages.AgeCheckPage;
import steam.pages.GamePage;
import steam.pages.MainPage;

import java.io.File;
import java.time.LocalDate;

public class SteamPurchaseAndDownloadTest extends BaseTest {
    private MainPage mainPage;
    private ActionPage actionPage;
    private AgeCheckPage ageCheckPage;
    private GamePage gamePage;
    private AboutPage aboutPage;

    @BeforeMethod
    public void steamTestSetUp() {
        File file = new File(BrowserFactory.PATH_DOWNLOAD_PACKAGE + AboutPage.STEAM_FILE_NAME);
        file.delete();
    }

    @Test
    public void steamPurchaseAndDownloadTest() {
        Logger.logSteps(1, 4);
        mainPage = new MainPage();
        mainPage.selectLanguageAndElementInSubMenu(languageName, PropertyLocalization.getBtnCategories(), PropertyLocalization.getBtnAction());
        Logger.logSteps(5, 6);
        actionPage = new ActionPage();
        String name = actionPage.selectSaleCategoryAndClickRandomGameWithMaxDiscount(PropertyLocalization.getBtnDiscount());
        if (AgeCheckPage.isAgePagePresent()) {
            Logger.logSteps(7, 8);
            ageCheckPage = new AgeCheckPage();
            ageCheckPage.enterDateOfBirth(LocalDate.of(1995, 12, 12));
        }
        Logger.logStep(9);
        gamePage = new GamePage();
        gamePage.verifyCurrentPageAndClickInstall(name);
        Logger.logStep(10);
        aboutPage = new AboutPage();
        aboutPage.downloadSteam();
    }
}
