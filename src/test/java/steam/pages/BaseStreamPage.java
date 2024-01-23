package steam.pages;

import framework.BasePage;
import framework.elements.BaseElement;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import steam.pages.components.HeaderComponent;

public class BaseStreamPage extends BasePage {
    protected BaseElement baseElement;
    protected final SoftAssert softAssert;
    protected HeaderComponent headerComponent;

    public BaseStreamPage(By locator, String currentPage) {
        super(locator, currentPage);
        this.headerComponent = new HeaderComponent();
        this.baseElement = new BaseElement();
        this.softAssert = new SoftAssert();
    }
}
