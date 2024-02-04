package pages;

import io.qameta.allure.Step;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.elements.FooterElements;


import java.util.ArrayList;
import java.util.List;

public class RegistrationPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='breadcrumb']/a[contains(@href,'/account-register.html')]")
    private WebElement breadcrumbTag;

    @FindBy(id = "registration_main_email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(id = "registration_main_firstname")
    private WebElement firstnameInput;

    @FindBy(id = "registration_main_telephone")
    private WebElement phoneInput;

    @FindBy(id = "registration_main_country_id")
    private WebElement countrySelectDropDown;

    @FindBy(id = "registration_main_zone_id")
    private WebElement regionSelectDropDown;

    @FindBy(id = "registration_main_city")
    private WebElement cityInput;

    @FindBy(xpath = ".//div[@class='simpleregister-button-right']")
    private WebElement continueButton;

    @FindBy(xpath = ".//span[@class='simplecheckout-error-text' and not(parent::td[@class='simplecheckout-customer-right']/input[@name='password'])]")
    private List<WebElement> listErrorsMessages;

    private String listErrorsMessagesLocator = ".//span[@class='simplecheckout-error-text' and not(parent::td[@class='simplecheckout-customer-right']/input[@name='password'])]";

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "account-register.html";
    }

    public FooterElements getFooter() {
        return new FooterElements(webDriver);
    }

    @Step("Проверить редирект на страницу быстрой регистрации")
    public RegistrationPage checkIsRedirectToRegistrationPage() {
        checkUrlWithPattern();
        checkIsElementVisible(breadcrumbTag);
        return this;
    }

    @Step("Ввести email в поле 'E-Mail'")
    public RegistrationPage enterTextIntoInputEmail(String email) {
        enterTextIntoInput(emailInput, email);
        return this;
    }

    @Step("Ввести пароль в поле 'Пароль'")
    public RegistrationPage enterTextIntoInputPassword(String password) {
        enterTextIntoInput(passwordInput, password);
        return this;
    }

    @Step("Ввести имя в поле 'Ім'я'")
    public RegistrationPage enterTextIntoInputFirstname(String firstname) {
        enterTextIntoInput(firstnameInput, firstname);
        return this;
    }

    @Step("Ввести номер телефона в поле 'Телефон'")
    public RegistrationPage enterTextIntoInputPhone(String phone) {
        enterTextIntoInput(phoneInput, phone);
        return this;
    }

    @Step("Выбрать значение в поле 'Країна'")
    public RegistrationPage selectTextInCountryDropDown(String valueInDropDown) {
        selectTextInDropDown(countrySelectDropDown, valueInDropDown);
        return this;
    }

    @Step("Выбрать значение в поле 'Регіон'")
    public RegistrationPage selectTextInRegionDropDown(String valueInDropDown) {
        selectTextInDropDown(regionSelectDropDown, valueInDropDown);
        return this;
    }

    @Step("Ввести название города в поле 'Місто'")
    public RegistrationPage enterTextIntoInputCity(String city) {
        enterTextIntoInput(cityInput, city);
        return this;
    }

    @Step("Нажать на кнопку 'Продовжити'")
    public RegistrationPage clickOnContinueButton() throws InterruptedException {
        scrollToElement(getFooter().facebookIcon);
        webDriverWaitLow.until(ExpectedConditions.elementToBeClickable(continueButton));
        clickOnElement(continueButton);
        return this;
    }

    @Step("Проверить вывод ошибок на странице регистрации")
    public LoginPage checkErrorsMessages(String messages) {
        //error1;error2 -> [error1, error2]
        String[] expectedErrors = messages.split(";");
        webDriverWaitLow.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsMessagesLocator), expectedErrors.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrors.length, listErrorsMessages.size());

        webDriverWaitLow.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsMessagesLocator), expectedErrors.length));

        ArrayList<String> actualErrors = new ArrayList<>();
        for (WebElement element : listErrorsMessages) {
            actualErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrors.length; i++) {
            softAssertions.assertThat(expectedErrors[i])
                    .as("Error " + i)
                    .isIn(actualErrors);
        }

        softAssertions.assertAll(); // проверка всех ошибок
        return null;
    }
}
