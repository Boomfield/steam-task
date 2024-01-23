package steam.pages;

import framework.CommonFunctions;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Predicate;

public class ActionPage extends BaseStreamPage {
    private static By lblMainTitle = By.xpath("//div[contains(@class,'ContentHubTitleCtn')]");
    private Button btnSaleCategory = new Button("//div[contains(@class,'saleitembrowser_FlavorLabel') and text()='%s']", "sale category");
    private Label lblDiscountInSaleList = new Label(By.xpath("//div[contains(@class,'StoreSaleWidgetContainer')]//div[contains(@class,'StoreSaleDiscountBox')]"), "discount");
    private Label lblGameDescriptionInMainBlock = new Label(By.xpath("//div[contains(@class,'animated_featured_capsule_AppRelevance')]"), "game description");
    private Button btnGameTitleInSaleList = new Button(By.xpath("//div[contains(@class,'alepreviewwidgets_Title')]//a[@href]"), "game title");
    private Button btnShowMore = new Button(By.xpath("//button[contains(@class,'ShowContentsButton')]"), "show more");
    private Button btnSaleGame = new Button(By.xpath("//div[contains(@class,'salepreviewwidgets_SaleItemBrowserRow')]"), "sale game");

    public ActionPage() {
        super(lblMainTitle, "Action page");
    }

    public void clickSaleCategory(String category) {
        lblGameDescriptionInMainBlock.isElementPresent();
        btnShowMore.scrollIntoView();
        btnSaleGame.isElementPresent();
        btnSaleCategory.getElementByText(category).clickElement();
    }

    public int getIndexOfGameWithMaxDiscountInList() {
        Predicate<WebElement> condition = x -> x.getText().contains(getMaxDiscount());
        return lblDiscountInSaleList.getRandomElementIndexByCondition(condition);
    }

    public String clickGameWithMaxDiscount() {
        int index = getIndexOfGameWithMaxDiscountInList();
        String gameName = btnGameTitleInSaleList.getElementTextInListByIndex(index);
        btnGameTitleInSaleList.clickElementInListByIndex(index);
        return gameName;
    }

    public String selectSaleCategoryAndClickRandomGameWithMaxDiscount(String category) {
        clickSaleCategory(category);
        btnSaleGame.isElementPresent();
        String gameName = clickGameWithMaxDiscount();
        baseElement.switchToLastWindow();
        return gameName;
    }

    private String getMaxDiscount() {
        int maxDiscount = lblDiscountInSaleList.getElementsTextList().stream()
                .mapToInt(x -> Integer.parseInt(CommonFunctions.removeMatchingText(x, "[-%]")))
                .max()
                .orElse(0);
        return String.valueOf(maxDiscount);
    }
}
