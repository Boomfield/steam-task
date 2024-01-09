package framework.elements;

import framework.Logger;
import framework.configuration.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class BaseElement {
    private Waiter waiter;
    private Actions actions;
    private By locator;
    private String xPath;
    private String elementName;

    public BaseElement() {
        this.waiter = new Waiter();
        this.actions = new Actions(Browser.getDriver());
    }

    public BaseElement(By locator, String elementName) {
        this();
        this.locator = locator;
        this.elementName = elementName;
    }

    public BaseElement(String xPath, String elementName) {
        this();
        this.xPath = xPath;
        this.elementName = elementName;
    }

    public void setLocatorByText(String text) {
        this.locator = By.xpath(String.format(xPath, text));
    }

    protected WebElement findElement(By locator) {
        waiter.isElementPresent(locator);
        return Browser.getDriver().findElement(locator);
    }

    private JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) Browser.getDriver();
    }

    private void highlightElement(WebElement element) {
        getJavascriptExecutor().executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public void clickElement() {
        WebElement el = waiter.waitElementIsClickable(locator);
        Logger.logInfo("click by " + elementName);
        highlightElement(el);
        el.click();
    }

    public void clickElementWithAction() {
        WebElement el = waiter.waitElementIsClickable(locator);
        Logger.logInfo("click by " + elementName);
        highlightElement(el);
        actions.click(el).perform();
    }

    public void clickElementInListByIndex(int index) {
        List<WebElement> elements = findElements(locator);
        WebElement element = elements.get(index);
        Logger.logInfo("click by " + elementName);
        getJavascriptExecutor().executeScript("arguments[0].click();", element);
    }

    public String getElementTextInListByIndex(int index) {
        List<WebElement> elements = findElements(locator);
        WebElement element = elements.get(index);
        return  element.getText();
    }

    public String getText() {
        return findElement(locator).getText();
    }

    protected List<WebElement> findElements(By locator) {
        return Browser.getDriver().findElements(locator);
    }

    public void moveMouseOnElement() {
        WebElement el = findElement(locator);
        highlightElement(el);
        Logger.logInfo("move mouse on " + elementName);
        actions.moveToElement(el).perform();
    }

    public void sendKey(String text) {
        WebElement el = findElement(locator);
        highlightElement(el);
        Logger.logInfo("send key in " + elementName);
        el.sendKeys(text);
    }

    public List<String> getElementsTextList() {
        List<WebElement> elements = findElements(locator);
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<WebElement> getElementsList() {
        List<WebElement> elements = findElements(locator);
        return elements;
    }

    public WebElement getRandomElementInList(List<WebElement> elements) {
        return elements.get(new Random().nextInt(elements.size()));
    }

    public void scrollIntoView() {
        getJavascriptExecutor().executeScript("arguments[0].scrollIntoView({block: 'center'});", findElement(locator));
    }

    public void isElementPresent() {
        waiter.isElementPresent(locator);
    }

    public void switchToLastWindow() {
        Set<String> windowHandles = Browser.getDriver().getWindowHandles();
        String newWindowHandle = windowHandles.stream().reduce((first, second) -> second).orElse(null);
        Browser.getDriver().switchTo().window(newWindowHandle);
    }
}
