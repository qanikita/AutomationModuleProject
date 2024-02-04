package addItemToCart;

import baseTest.BaseTest;
import org.junit.Test;


public class addItemToCart extends BaseTest {

    @Test
    public void addProductToCart() throws InterruptedException {
        pageProvider.welcomePage().openWelcomePage()
                .checkIsRedirectToWelcomePage()
                .getHeader().clickOnStockLink()
                .checkIsRedirectToStockPage();
        String[] itemData = pageProvider.stockPage().clickRandomCategoryTab();
        String itemName = itemData[0];
        String itemPrice = itemData[1];
        pageProvider.stockPage().getShoppingBasket().checkTextInSuccessMessage(itemName)
                .clickOnSubmitButtonWithInvalidData()
                .checkIsRedirectToCheckoutPage()
                .isPriceMatchingName(itemName, itemPrice);
    }
}
