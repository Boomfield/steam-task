package framework;

import framework.elements.Waiter;
import org.openqa.selenium.By;

public abstract class BasePage {
    private final By locator;
    private final String currentPage;
    private final Waiter waiter;

    protected BasePage(By locator, String currentPage) {
        this.waiter = new Waiter();
        this.locator = locator;
        this.currentPage = currentPage;
        assertIsOpen();
    }

    protected void assertIsOpen() {
        try {
            waiter.waitForElementPresent(locator);
            Logger.logInfo(currentPage + " is open");
        } catch (Exception e) {
            Logger.logFatal(currentPage + "doesn't open");
        }
    }
}