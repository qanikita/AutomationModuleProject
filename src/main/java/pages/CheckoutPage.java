package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='breadcrumb']/a[contains(@href,'/checkout.html')]")
    private WebElement breadcrumbTag;

    String rowLocator = ".//table[@class='simplecheckout-cart']//tr[td[@class='name']/a[text()='%s']]";

    public CheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "checkout.html";
    }

    @Step("Проверить редирект на страницу проверки заказа")
    public CheckoutPage checkIsRedirectToCheckoutPage() {
        checkUrlWithPattern();
        checkIsElementVisible(breadcrumbTag);
        return this;
    }

    public CheckoutPage isPriceMatchingName(String expectedName, String expectedPrice) throws InterruptedException {
        WebElement row = webDriver.findElement(By.xpath(String.format(rowLocator, expectedName)));
        scrollToElement(row);
        WebElement nameElement = row.findElement(By.xpath(".//td[@class='name']"));
        WebElement priceElement = row.findElement(By.xpath(".//td[@class='price']"));

        String actualName = nameElement.getText();
        String actualPrice = priceElement.getText();
        Assert.assertTrue("There is no product with this name in the table", expectedName.equals(actualName));
        Assert.assertTrue("The price does not match the product", expectedPrice.equals(actualPrice));
        logger.info(actualName + " corresponds to a price of " + actualPrice);
        return this;
    }
}
