package steam.pages;

import framework.elements.Button;
import framework.elements.DropDown;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import steam.pages.helpers.Language;

public class MainPage extends BaseSteamPage {
    private DropDown ddmCategoryMainMenu = new DropDown("//a[@class='pulldown_desktop' and text()='%s']", "main menu");
    private Button btnCategorySubMenu = new Button("//a[@class='popup_menu_item' and normalize-space()='%s']", "sub menu");
    private static By lblTitle = By.className("home_page_content");

    public MainPage() {
        super(lblTitle, "Main page");
    }

    private void selectDropDownMenuElement(String mainMenu) {
        ddmCategoryMainMenu.getElementByText(mainMenu).moveMouseOnElement();
    }

    private void clickSubMenuElement(String subMenu) {
        btnCategorySubMenu.getElementByText(subMenu).clickElementAndWait();
    }

    @Step("Select language and element in sub menu")
    public void selectLanguageAndElementInSubMenu(Language language, String mainMenu, String subMenu) {
        headerComponent.selectLanguageInHeader(language);
        selectDropDownMenuElement(mainMenu);
        clickSubMenuElement(subMenu);
    }
}