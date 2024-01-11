package steam.pages;

import framework.BaseForm;
import framework.elements.BaseElement;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import steam.pages.components.HeaderComponent;

public class BasePage extends BaseForm {
    protected BaseElement baseElement;
    protected final SoftAssert softAssert;
    protected HeaderComponent headerComponent;

    public BasePage(By locator, String currentPage) {
        super(locator, currentPage);
        this.headerComponent = new HeaderComponent();
        this.baseElement = new BaseElement();
        this.softAssert = new SoftAssert();
    }
}
