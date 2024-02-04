package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PersonalAreaPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='breadcrumb']/a[contains(@href,'/my-account.html')]")
    private WebElement breadcrumbTag;

    @FindBy(xpath = ".//div[@class='content']//a[contains(@href,'change-password.html')]")
    private WebElement changePasswordLink;

    @FindBy(xpath = ".//div[@class='content']//a[contains(@href,'newsletter.html')]")
    private WebElement subscribeLink;

    @FindBy(xpath = ".//div[@class='success']")
    private WebElement successMessage;

    public PersonalAreaPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "my-account.html";
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    @Step("Проверить редирект на страницу кабинета пользователя")
    public PersonalAreaPage checkIsRedirectToPersonalAreaPage() {
        checkUrlWithPattern();
        checkIsElementVisible(breadcrumbTag);
        return this;
    }

    @Step("Нажать на линк 'Змінити пароль'")
    public ChangePasswordPage clickOnLoginLink() {
        clickOnElement(changePasswordLink);
        return new ChangePasswordPage(webDriver);
    }

    @Step("Нажать на линк 'Редагувати підписку'")
    public SubscribePage clickOnSubscribeLink() {
        clickOnElement(subscribeLink);
        return new SubscribePage(webDriver);
    }

    @Step("Проверить текст success-сообщения")
    public PersonalAreaPage checkTextInSuccessMessage(String text) {
        checkTextInElement(successMessage, text);
        return this;
    }
}
