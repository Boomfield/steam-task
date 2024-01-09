package steam.pages.mainPage;

import framework.elements.Button;
import framework.elements.DropDown;
import org.openqa.selenium.By;
import steam.pages.BasePage;
import steam.pages.components.HeaderComponent;

public class MainPage extends BasePage {
    private HeaderComponent headerComponent;
    private DropDown ddmCategoryMainMenu = new DropDown("//a[@class='pulldown_desktop' and text()='%s']", "main menu");
    private Button btnCategorySubMenu = new Button("//a[@class='popup_menu_item' and normalize-space()='%s']", "sub menu");
    private static By lblTitle = By.className("home_page_content");

    public MainPage() {
        super(lblTitle);
        this.headerComponent = new HeaderComponent();
    }

    private void clickDropDownMenuElement(String mainMenu) {
        ddmCategoryMainMenu.setLocatorByText(mainMenu);
        ddmCategoryMainMenu.moveMouseOnElement();
    }

    private void clickSubMenuElement(String subMenu) {
        btnCategorySubMenu.setLocatorByText(subMenu);
        btnCategorySubMenu.clickElement();
    }

    public void selectLanguageAndElementInSubMenu(String language, String mainMenu, String subMenu) {
        headerComponent.selectLanguageInHeader(language);
        clickDropDownMenuElement(mainMenu);
        clickSubMenuElement(subMenu);
    }
}