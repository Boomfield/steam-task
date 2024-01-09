package steam.pages;

import framework.BaseForm;
import framework.elements.BaseElement;
import framework.elements.Waiter;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

public class BasePage extends BaseForm {
    protected BaseElement baseElement;
    protected final SoftAssert softAssert;
    protected Waiter waiter;

    public BasePage( By locator) {
        super(locator);
        this.baseElement = new BaseElement();
        this.waiter = new Waiter();
        this.softAssert = new SoftAssert();
    }
}
