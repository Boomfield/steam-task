package steam.pages.ageForm;

import framework.elements.Button;
import framework.elements.DropDown;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class AgeCheckForm {
    private DropDown ddbAgeDay = new DropDown(By.id("ageDay"), "age day");
    private DropDown ddbAgeMonth = new DropDown("//select[@name='ageMonth']//option[contains(@value,'')][%s]", "age month");
    private DropDown ddbAgeYear = new DropDown(By.id("ageYear"), "age year");
    private Button btnViewProduct = new Button(By.id("view_product_page_btn"), "view product");

    public void enterDateOfBirth(String dateOfBirth) {
        try {
            btnViewProduct.isElementPresent();
            String[] parts = dateOfBirth.split("\\.");
            ddbAgeDay.sendKey(parts[0]);
            ddbAgeMonth.setLocatorByText(parts[1]);
            ddbAgeMonth.clickElement();
            ddbAgeYear.sendKey(parts[2]);
            btnViewProduct.clickElement();
        }catch (NoSuchElementException e) {

        }
    }
}
