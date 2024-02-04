package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.elements.ShoppingBasket;

import java.util.List;
import java.util.Random;

public class StockPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='breadcrumb']/a[contains(@href,'/specials.html')]")
    private WebElement breadcrumbTag;

    @FindBy(xpath = ".//div[@id='content']//span[text()='Купити']/..")
    private List<WebElement> itemsList;

    @FindBy(xpath = ".//div[@id='content']//div[contains(@class,'out-of-stock')]")
    private List<WebElement> disabledItemsList;

    String itemsListLocator = ".//div[@id='content']//span[text()='Купити']/..";
    String itemNameLocator = "(%s)[%s]/ancestor::div[@class='item']//div[@class='name']";
    String itemPriceLocator = "(%s)[%s]/ancestor::div[@class='item']//div[@class='price']/span[@class='price-new']";


    public StockPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "specials.html";
    }

    public ShoppingBasket getShoppingBasket() {
        return new ShoppingBasket(webDriver);
    }

    @Step("Проверить редирект на страницу акции")
    public StockPage checkIsRedirectToStockPage() {
        checkUrlWithPattern();
        checkIsElementVisible(breadcrumbTag);
        return this;
    }

    @Step("Нажать на любой товар из списка")
    public String[] clickRandomCategoryTab() throws InterruptedException {
        if (!itemsList.isEmpty()) {
            moveToElement(itemsList.get(0));
            webDriverWaitHigh.until(ExpectedConditions.invisibilityOfAllElements(disabledItemsList));
            Random random = new Random();
            int randomIndex = random.nextInt(itemsList.size());
            WebElement randomCategoryTab = itemsList.get(randomIndex);
            scrollToElement(randomCategoryTab);
            clickOnElement(randomCategoryTab);

            String itemNameXPath = String.format(itemNameLocator, itemsListLocator, randomIndex + 1);
            String itemPriceXPath = String.format(itemPriceLocator, itemsListLocator, randomIndex + 1);
            String itemNameText = webDriver.findElement(By.xpath(itemNameXPath)).getText();
            String itemPriceText = webDriver.findElement(By.xpath(itemPriceXPath)).getText();

            logger.info("Item Name: " + itemNameText);
            logger.info("Item Price: " + itemPriceText);

            return new String[]{itemNameText, itemPriceText};
        } else {
            logger.error("No items");
            Assert.fail("No items");
            return new String[]{""};
        }
    }
}
