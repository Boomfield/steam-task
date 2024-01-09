package steam.pages.actionPage;

import framework.CommonFunction;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import steam.pages.BasePage;

import java.util.List;

public class ActionPage extends BasePage {
    private Button btnSaleCategory = new Button("//div[contains(@class,'saleitembrowser_FlavorLabel') and text()='%s']", "sale category");
    private Label lblDiscountInSaleList = new Label(By.xpath("//div[contains(@class,'StoreSaleWidgetContainer')]//div[contains(@class,'StoreSaleDiscountBox')]"), "discount");
    private Label lblGameDescriptionInMainBlock = new Label(By.xpath("//div[contains(@class,'animated_featured_capsule_AppRelevance')]"), "game description");
    private Button btnGameTitleInSaleList = new Button(By.xpath("//div[contains(@class,'alepreviewwidgets_Title')]//a[@href]"), "game title");
    private Button btnShowMore = new Button(By.xpath("//button[contains(@class,'ShowContentsButton')]"), "show more");
    private Button btnSaleGame = new Button(By.xpath("//div[contains(@class,'salepreviewwidgets_SaleItemBrowserRow')]"), "sale game");
    private static By lblMainTitle = By.xpath("//div[contains(@class,'ContentHubTitleCtn')]");

    public ActionPage() {
        super(lblMainTitle);
    }

    public void clickSaleItem(String name) {
        lblGameDescriptionInMainBlock.isElementPresent();
        btnShowMore.scrollIntoView();
        btnSaleGame.isElementPresent();
        btnSaleCategory.setLocatorByText(name);
        btnSaleCategory.clickElement();
    }

    public List<String> getListOfDiscount() {
        return lblDiscountInSaleList.getElementsTextList();
    }

    public int getGameListWithDiscount() {
        List<WebElement> allGames = lblDiscountInSaleList.getElementsList();
        List<WebElement> gamesWithSameDiscount = lblDiscountInSaleList.getElementsList()
                .stream()
                .filter(x->x.getText().contains(getMaxDiscount(getListOfDiscount())))
                .toList();
        WebElement randomGame = baseElement.getRandomElementInList(gamesWithSameDiscount);
        return allGames.indexOf(randomGame);
    }

    public String clickElementWithMaxDiscount() {
        int index = getGameListWithDiscount();
        String gameName = btnGameTitleInSaleList.getElementTextInListByIndex(index);
        btnGameTitleInSaleList.clickElementInListByIndex(index);
        return gameName;
    }

    public String getGameWithMaxDiscountAndName(String name) {
        clickSaleItem(name);
        btnSaleGame.isElementPresent();
        String gameName = clickElementWithMaxDiscount();
        baseElement.switchToLastWindow();
        return gameName;
    }

    private String getMaxDiscount(List<String> discountList) {
        int maxDiscount = discountList.stream()
                .mapToInt(x -> CommonFunction.regexGetMatch(x, "[-%]"))
                .max()
                .orElse(0);
        return String.valueOf(maxDiscount);
    }
}
