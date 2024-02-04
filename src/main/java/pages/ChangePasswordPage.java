package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChangePasswordPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='breadcrumb']/a[contains(@href,'/change-password.html')]")
    private WebElement breadcrumbTag;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirm")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = ".//input[@type='submit']")
    private WebElement submitButton;

    @Step("Ввести пароль в поле 'Пароль'")
    public ChangePasswordPage enterTextIntoInputPassword(String password) {
        enterTextIntoInput(passwordInput, password);
        return this;
    }

    @Step("Ввести пароль в поле 'Підтвердіть пароль'")
    public ChangePasswordPage enterTextIntoInputConfirmPasswordInput(String password) {
        enterTextIntoInput(confirmPasswordInput, password);
        return this;
    }

    @Step("Нажать на кнопку 'Продовжити'")
    public PersonalAreaPage clickOnSubmitButton() {
        clickOnElement(submitButton);
        return new PersonalAreaPage(webDriver);
    }

    public ChangePasswordPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "change-password.html";
    }

    @Step("Проверить редирект на страницу кабинета пользователя")
    public ChangePasswordPage checkIsRedirectToChangePasswordPage() {
        checkUrlWithPattern();
        checkIsElementVisible(breadcrumbTag);
        return this;
    }
}
