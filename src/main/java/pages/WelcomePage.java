package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class WelcomePage extends ParentPage {

    @FindBy(xpath = ".//div[contains(@class,'welcomemod')]")
    private WebElement welcomeBanner;

    public WelcomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "";
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    @Step("Проверить редирект на страницу приветствия")
    public WelcomePage checkIsRedirectToWelcomePage() {
        checkUrlWithPattern();
        checkIsElementVisible(welcomeBanner);
        return this;
    }

    @Step("Открыть страницу приветствия")
    public WelcomePage openWelcomePage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened " + baseUrl);
            return this;
        } catch (Exception e) {
            logger.error("Can not open login page");
            Assert.fail("Can not open login page");
            return null;
        }
    }
}
