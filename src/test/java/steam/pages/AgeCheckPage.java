package steam.pages;

import framework.elements.Button;
import framework.elements.DropDown;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.LocalDate;

public class AgeCheckPage extends BaseSteamPage {
    private static By lblRootElement = By.className("age_gate");
    private DropDown ddbAgeDay = new DropDown(By.id("ageDay"), "age day");
    private DropDown ddbAgeMonth = new DropDown("//select[@name='ageMonth']//option[contains(@value,'')][%s]", "age month");
    private DropDown ddbAgeYear = new DropDown(By.id("ageYear"), "age year");
    private static Button btnViewProduct = new Button(By.id("view_product_page_btn"), "view product");

    public AgeCheckPage() {
        super(lblRootElement, "Age check page");
    }

    @Step("Enter date of birth")
    public void enterDateOfBirth(LocalDate dateOfBirth) {
        ddbAgeDay.sendKey(String.valueOf(dateOfBirth.getDayOfMonth()));
        ddbAgeMonth.getElementByText(String.valueOf(dateOfBirth.getMonth().getValue())).clickElement();
        ddbAgeYear.sendKey(String.valueOf(dateOfBirth.getYear()));
        btnViewProduct.clickElementAndWait();
    }

    public static boolean isAgePagePresent() {
        return btnViewProduct.isElementPresent();
    }
}
