package pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {

    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WelcomePage welcomePage() {
        return new WelcomePage(webDriver);
    }

    public PersonalAreaPage personalAreaPage() {
        return new PersonalAreaPage(webDriver);
    }

    public LoginPage loginPagePage() {
        return new LoginPage(webDriver);
    }

    public StockPage stockPage() {
        return new StockPage(webDriver);
    }

    public CheckoutPage checkoutPage() {
        return new CheckoutPage(webDriver);
    }
}
