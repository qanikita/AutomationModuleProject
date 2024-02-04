package pages;

import libs.ConfigProvider;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWaitDefoult, webDriverWaitLow, webDriverWaitHigh;

    // Присвоение полученного webDriver, инициализация всех объектов страницы, которые обозначены аннотацией @FindBy и
    // создание объектов WebDriverWait с определенным временем ожидания
    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // инициализирует все элементы на странице отмеченные аннотацией @FindBy
        webDriverWaitDefoult = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
        webDriverWaitLow = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWaitHigh = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_HIGH()));
    }

    // Метод для ввода текста в поле
    protected void enterTextIntoInput(WebElement input, String text) {
        try {
            input.clear();
            input.sendKeys(text);
            logger.info(text + " was inputted into input " + getElementName(input));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // Метод для вывода ошибки и остановки теста
    private void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    // Метод для получения имени элемента
    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    // Метод для нажатия на элемент, заданный при помощи WebElement
    protected void clickOnElement(WebElement element) {
        try {
            webDriverWaitDefoult.withMessage("Element is not clickable").until(ExpectedConditions.elementToBeClickable(element));
            String elementName = getElementName(element);
            element.click();
            logger.info("Element was clicked " + elementName);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // Метод для нажатия на элемент, заданный при помощи стрингового значения локатора
    protected void clickOnElement(String locator) {
        try {
            clickOnElement(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // Метод проверяет отображение элемента и возвращает boolean значение
    protected boolean isElementDisplayed(WebElement element) {
        try {
            boolean state = element.isDisplayed();
            logger.info("Element " + getElementName(element) + " is displayed (" + state + ")");
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed (false) " + e);
            return false;
        }
    }

    // Метод выбирает пункт из дрпдауна по тексту
    protected void selectTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // Метод выбирает пункт из дрпдауна по значению
    protected void selectValueInDropDown(WebElement dropDown, String value) {
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in DropDown" + getElementName(dropDown));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // Метод проверяет отображение элемента
    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }

    // Метод проверяет отсутствие элемента
    protected void checkIsElementNotVisible(WebElement webElement) {
        Assert.assertFalse("Element is visible", isElementDisplayed(webElement));
    }

    // Метод проверяет присутствие полного совпадения текста в элементе
    protected void checkTextInElement(WebElement element, String expectedText) {
        try {
            String textFromElement = element.getText();
            Assert.assertEquals("Text in element not matched", expectedText, textFromElement);
            logger.info("Text '" + expectedText + "' in element matched");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // Метод отмечает чекбокс
    protected void checkCheckbox(WebElement checkbox) {
        try {
            if (!checkbox.isSelected()) {
                checkbox.click();
                logger.info("Checkbox was checked");
            } else {
                logger.info("Checkbox is already checked");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // Метод снимает отмеченное значение с чекбокса
    protected void uncheckCheckbox(WebElement checkbox) {
        try {
            if (checkbox.isSelected()) {
                checkbox.click();
                logger.info("Checkbox was unchecked");
            } else {
                logger.info("Checkbox is already unchecked");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // Метод для скролла к нужному элементу
    public void scrollToElement(WebElement element) throws InterruptedException {
        try {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(200); //Другие ожидания не работают
        } catch (WebDriverException e) {
            printErrorAndStopTest(e);
        }
    }

    // Метод присваивает пустое значение стринге, если в ней есть значение "empty_string"
    public static String emptyStringOutput(String value) {
        if ("empty_string".equals(value)) {
            return "";
        } else {
            return value;
        }
    }

    // Метод наводит курсор мыши на элемент
    public void moveToElement (WebElement element) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element);
        actions.perform();
    }
}
