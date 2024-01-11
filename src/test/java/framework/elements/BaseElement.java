package framework.elements;

import framework.Logger;
import framework.configuration.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BaseElement {
    private Waiter waiter;
    private Actions actions;
    private By locatorType;
    private String locatorValue;
    private String elementName;

    public BaseElement() {
        this.waiter = new Waiter();
        this.actions = new Actions(Browser.getDriver());
    }

    public BaseElement(By locatorType, String elementName) {
        this();
        this.locatorType = locatorType;
        this.elementName = elementName;
    }

    public BaseElement(String locatorValue, String elementName) {
        this();
        this.locatorValue = locatorValue;
        this.elementName = elementName;
    }

    public BaseElement getElementByText(String text) {
        return new BaseElement(By.xpath(String.format(locatorValue, text)), elementName);
    }

    protected WebElement findElement(By locator) {
        waiter.waitForElementPresent(locator);
        return Browser.getDriver().findElement(locator);
    }

    private JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) Browser.getDriver();
    }

    private void highlightElement(WebElement element) {
        getJavascriptExecutor().executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public void clickElement() {
        Logger.logInfo("click by " + elementName);
        WebElement el = waiter.waitElementIsClickable(locatorType);
        highlightElement(el);
        el.click();
    }

    public void clickElementWithAction() {
        Logger.logInfo("click by " + elementName);
        WebElement el = waiter.waitElementIsClickable(locatorType);
        highlightElement(el);
        actions.click(el).perform();
    }

    public void clickElementInListByIndex(int index) {
        Logger.logInfo("click by " + elementName);
        List<WebElement> elements = findElements(locatorType);
        WebElement element = elements.get(index);
        getJavascriptExecutor().executeScript("arguments[0].click();", element);
    }

    public String getElementTextInListByIndex(int index) {
        List<WebElement> elements = findElements(locatorType);
        WebElement element = elements.get(index);
        return element.getText();
    }

    public String getText() {
        return findElement(locatorType).getText();
    }

    public String getElementAttributeBy(String attributeName) {
        return findElement(locatorType).getAttribute(attributeName);
    }

    protected List<WebElement> findElements(By locator) {
        return Browser.getDriver().findElements(locator);
    }

    public void moveMouseOnElement() {
        Logger.logInfo("move mouse on " + elementName);
        WebElement el = findElement(locatorType);
        highlightElement(el);
        actions.moveToElement(el).perform();
    }

    public void sendKey(String text) {
        Logger.logInfo("send key in " + elementName);
        WebElement el = findElement(locatorType);
        highlightElement(el);
        el.sendKeys(text);
    }

    public List<String> getElementsTextList() {
        List<WebElement> elements = findElements(locatorType);
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<WebElement> getElementsList() {
        return findElements(locatorType);
    }

    public WebElement getRandomElementInList(List<WebElement> elements) {
        return elements.get(new Random().nextInt(elements.size()));
    }

    public int getRandomElementIndexByCondition(Predicate<WebElement> condition) {
        List<WebElement> elements = getElementsList();
        List<WebElement> filteredElements = elements.stream()
                .filter(condition)
                .toList();
        return elements.indexOf(getRandomElementInList(filteredElements));
    }

    public void scrollIntoView() {
        getJavascriptExecutor().executeScript("arguments[0].scrollIntoView({block: 'center'});", findElement(locatorType));
    }

    public boolean isElementPresent() {
        return waiter.waitForElementPresent(locatorType);
    }

    public void switchToLastWindow() {
        Set<String> windowHandles = Browser.getDriver().getWindowHandles();
        String newWindowHandle = windowHandles.stream().reduce((first, second) -> second).orElse(null);
        Browser.getDriver().switchTo().window(newWindowHandle);
    }
}
