package steam.pages.components;

import framework.elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class HeaderComponent {
    private Button btnLanguage = new Button(By.id("language_pulldown"), "language");
    private Button btnInstallSteam = new Button(By.className("header_installsteam_btn_content"), "install steam");
    private Button btnChooseLanguage = new Button("//a[@class='popup_menu_item tight' and contains(text(),'%s')]", "choose language");

    public void selectLanguage(String language) {
        btnChooseLanguage.setLocatorByText(language);
        try {
            btnChooseLanguage.clickElement();
        } catch (TimeoutException ignored) {
        }
    }

    public void clickLanguage() {
        btnLanguage.clickElement();
    }

    public void clickInstallSteam() {
        btnInstallSteam.clickElement();
    }

    public void selectLanguageInHeader(String language) {
        clickLanguage();
        selectLanguage(language);
    }
}
