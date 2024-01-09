package framework;

import framework.elements.Waiter;
import org.openqa.selenium.By;

public abstract class BaseForm {

    private final By locator;
    private final Waiter waiter;

    protected BaseForm(By locator) {
        this.waiter = new Waiter();
        this.locator = locator;
        assertIsOpen();
    }

    protected void assertIsOpen() {
        if (!waiter.isElementPresent(locator)) {
            throw new RuntimeException("Page is not open.");
        }
    }
}
