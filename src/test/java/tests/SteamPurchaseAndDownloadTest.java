package tests;

import framework.Logger;
import framework.configuration.Browser;
import framework.helpers.PropertyLocalizationHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steam.pages.aboutPage.AboutPage;
import steam.pages.actionPage.ActionPage;
import steam.pages.ageForm.AgeCheckForm;
import steam.pages.gamePage.GamePage;
import steam.pages.mainPage.MainPage;

import java.io.File;

public class SteamPurchaseAndDownloadTest extends BaseTest {
    private MainPage mainPage;
    private ActionPage actionPage;
    private AgeCheckForm ageCheckForm;
    private GamePage gamePage;
    private AboutPage aboutPage;

    @BeforeMethod
    public void steamTestSetUp() {
        File file = new File(Browser.PATH_DOWNLOAD_PACKAGE + AboutPage.STEAM_FILE_NAME);
        file.delete();
    }

    @Test
    public void steamPurchaseAndDownloadTest() {
        mainPage = new MainPage();
        Logger.logSteps(1, 4);
        mainPage.selectLanguageAndElementInSubMenu(PropertyLocalizationHelper.getLanguage(), PropertyLocalizationHelper.getBtnCategories(), PropertyLocalizationHelper.getBtnAction());
        actionPage = new ActionPage();
        Logger.logSteps(5, 6);
        String name = actionPage.getGameWithMaxDiscountAndName(PropertyLocalizationHelper.getBtnDiscount());
        ageCheckForm = new AgeCheckForm();
        Logger.logSteps(7, 8);
        ageCheckForm.enterDateOfBirth("12.12.1995");
        gamePage = new GamePage();
        Logger.logStep(9);
        gamePage.verifyCurrentPageAndClickInstall(name);
        aboutPage = new AboutPage();
        Logger.logStep(10);
        aboutPage.downloadSteam();
    }
}
