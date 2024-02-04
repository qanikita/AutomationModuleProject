package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static data.TestData.VALID_EMAIL;
import static data.TestData.VALID_PASSWORD;

public class LoginPage extends ParentPage {

    WelcomePage welcomePage = new WelcomePage(webDriver);

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = ".//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = ".//div[@class='warning']")
    private WebElement warningMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "login.html";
    }

    @Step("Проверить редирект на страницу логина")
    public LoginPage checkIsRedirectToLoginPage() {
        checkUrlWithPattern();
        checkIsElementVisible(submitButton);
        return this;
    }

    @Step
    public PersonalAreaPage openLoginPageAndFillLoginFormWithValidCred() {
        welcomePage.openWelcomePage()
                .checkIsRedirectToWelcomePage().
                getHeader().clickOnLoginLink()
                .checkIsRedirectToLoginPage()
                .enterTextIntoInputEmail(VALID_EMAIL)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnSubmitButton()
                .checkIsRedirectToPersonalAreaPage()
                .getHeader().checkIsLoginLinkNotVisible();
        return new PersonalAreaPage(webDriver);
    }

    @Step("Ввести email в поле 'E-Mail'")
    public LoginPage enterTextIntoInputEmail(String email) {
        enterTextIntoInput(emailInput, email);
        return this;
    }

    @Step("Ввести email в поле 'Пароль'")
    public LoginPage enterTextIntoInputPassword(String password) {
        enterTextIntoInput(passwordInput, password);
        return this;
    }

    @Step("Нажать на кнопку 'УВІЙТИ'")
    public PersonalAreaPage clickOnSubmitButton() {
        clickOnElement(submitButton);
        return new PersonalAreaPage(webDriver);
    }

    @Step("Нажать на кнопку 'УВІЙТИ' с не валидными данными")
    public LoginPage clickOnSubmitButtonWithInvalidData() {
        clickOnElement(submitButton);
        return new LoginPage(webDriver);
    }

    @Step("Проверить текст warning-сообщения")
    public LoginPage checkTextInWarningMessage(String text) {
        checkTextInElement(warningMessage, text);
        return this;
    }
}
