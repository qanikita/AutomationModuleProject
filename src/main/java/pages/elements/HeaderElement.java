package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.*;

public class HeaderElement extends CommonActionsWithElements {

    @FindBy(xpath = ".//div[@id='login-float']//a[contains(@href,'login')]")
    private WebElement loginLink;

    @FindBy(xpath = ".//div[@id='login-float']//a[contains(@href,'account-register')]")
    private WebElement registrationLink;

    @FindBy(xpath = ".//div[@id='login-float']//a[contains(@href,'logout')]")
    private WebElement logoutLink;

    @FindBy(xpath = ".//div[@id='header-links']//a[contains(@href,'specials')]")
    private WebElement stockLink;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать на линк 'Увійти'")
    public LoginPage clickOnLoginLink() {
        clickOnElement(loginLink);
        return new LoginPage(webDriver);
    }

    @Step("Проверить отсутствие линка 'Увійти'")
    public HeaderElement checkIsLoginLinkNotVisible() {
        checkIsElementNotVisible(loginLink);
        return this;
    }

    @Step("Нажать на линк 'Реєстрація'")
    public RegistrationPage clickOnRegistrationLink() {
        clickOnElement(registrationLink);
        return new RegistrationPage(webDriver);
    }

    @Step("Нажать на линк 'Вийти'")
    public WelcomePage clickOnLogoutLink() {
        clickOnElement(logoutLink);
        return new WelcomePage(webDriver);
    }

    @Step("Нажать на ссылку 'Акції'")
    public StockPage clickOnStockLink() {
        clickOnElement(stockLink);
        return new StockPage(webDriver);
    }
}
