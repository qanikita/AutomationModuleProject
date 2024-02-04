package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CheckoutPage;
import pages.CommonActionsWithElements;

public class ShoppingBasket extends CommonActionsWithElements {


    @FindBy(xpath = ".//div[@class='message']")
    private WebElement successMessage;

    @FindBy(xpath = ".//div[@class='success']//a[contains(@href,'checkout.html')]")
    private WebElement checkoutButton;

    public ShoppingBasket(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить текст в сообщении об добавлении товара в корзину")
    public ShoppingBasket checkTextInSuccessMessage(String text) {
        checkTextInElement(successMessage, text + " додано до кошика!");
        return this;
    }

    @Step("Нажать на кнопку 'Оформити замовлення'")
    public CheckoutPage clickOnSubmitButtonWithInvalidData() {
        clickOnElement(checkoutButton);
        return new CheckoutPage(webDriver);
    }
}
