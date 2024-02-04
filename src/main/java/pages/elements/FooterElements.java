package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;

public class FooterElements  extends CommonActionsWithElements {

    @FindBy(xpath = ".//footer//a[@href='https://sota.kh.ua/ua/specials.html']")
    public WebElement facebookIcon;

    public FooterElements(WebDriver webDriver) {
        super(webDriver);
    }
}
