package steam.pages.components;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import steam.pages.helpers.Language;

public class HeaderComponent {
    private Label lblHtml = new Label(By.xpath("//html"), "html");
    private Button btnLanguage = new Button(By.id("language_pulldown"), "language");
    private Button btnInstallSteam = new Button(By.className("header_installsteam_btn_content"), "install steam");
    private Button btnChooseLanguage = new Button("//a[@class='popup_menu_item tight' and contains(text(),'%s')]", "choose language");

    public void clickLanguage(Language language) {
        if (!language.getLanguageCode().equals(getCurrentLanguage())) {
            btnChooseLanguage.getElementByText(language.getDisplayName()).clickElement();
        }
    }

    public String getCurrentLanguage() {
        return lblHtml.getElementAttributeBy("lang");
    }

    public void clickLanguageIcon() {
        btnLanguage.clickElement();
    }

    public void clickInstallSteam() {
        btnInstallSteam.clickElement();
    }

    public void selectLanguageInHeader(Language language) {
        clickLanguageIcon();
        clickLanguage(language);
    }
}
