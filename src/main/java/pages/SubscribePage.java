package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubscribePage extends ParentPage {

    @FindBy(xpath = ".//div[@class='breadcrumb']/a[contains(@href,'/newsletter.html')]")
    private WebElement breadcrumbTag;

    @FindBy(xpath = ".//input[@value='1']")
    private WebElement checkedYes;

    @FindBy(xpath = ".//input[@value='0']")
    private WebElement checkedNo;

    @FindBy(xpath = ".//input[@type='submit']")
    private WebElement submitButton;

    public SubscribePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "newsletter.html";
    }

    @Step("Проверить редирект на страницу подписки")
    public SubscribePage checkIsRedirectToSubscribePage() {
        checkUrlWithPattern();
        checkIsElementVisible(breadcrumbTag);
        return this;
    }

    @Step("Принять или отклонить подписку")
    public SubscribePage acceptOrDeclineSubscription(String value) {
        if (value.equals("yes")) {
            checkCheckbox(checkedYes);
        } else if (value.equals("no")) {
            uncheckCheckbox(checkedNo);
        } else {
            logger.error("State should be 'check' or 'uncheck'");
            Assert.fail("State should be 'check' or 'uncheck'");
        }
        return this;
    }

    @Step("Нажать на кнопку 'Продовжити'")
    public PersonalAreaPage clickOnSubmitButton() {
        clickOnElement(submitButton);
        return new PersonalAreaPage(webDriver);
    }
}
