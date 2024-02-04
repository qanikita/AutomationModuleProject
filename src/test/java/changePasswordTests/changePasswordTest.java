package changePasswordTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelSpreadsheetData;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

import static data.TestData.VALID_EMAIL;
import static data.TestData.VALID_PASSWORD;

@RunWith(JUnitParamsRunner.class)
public class changePasswordTest extends BaseTest {

    String NEW_PASSWORD;
    String SUCCESS_MESSAGE;

    public void loginAndGoToPersonalAreaPage(String email, String password) {
        pageProvider.welcomePage().openWelcomePage()
                .checkIsRedirectToWelcomePage()
                .getHeader().clickOnLoginLink()
                .checkIsRedirectToLoginPage()
                .enterTextIntoInputEmail(email)
                .enterTextIntoInputPassword(password)
                .clickOnSubmitButton()
                .checkIsRedirectToPersonalAreaPage()
                .getHeader().checkIsLoginLinkNotVisible();
    }


    @Test
    @Parameters(method = "parametersForChangePasswordTests")
    public void changePassword(String email, String password, String newPassword, String successMessage, String warningMessage) {
        NEW_PASSWORD = newPassword;
        SUCCESS_MESSAGE = successMessage;
        loginAndGoToPersonalAreaPage(email, password);
        pageProvider.personalAreaPage().clickOnLoginLink()
                .checkIsRedirectToChangePasswordPage()
                .enterTextIntoInputPassword(newPassword)
                .enterTextIntoInputConfirmPasswordInput(newPassword)
                .clickOnSubmitButton()
                .checkTextInSuccessMessage(successMessage)
                .checkIsRedirectToPersonalAreaPage()
                .getHeader().clickOnLogoutLink()
                .checkIsRedirectToWelcomePage()
                .getHeader().clickOnLoginLink()
                .checkIsRedirectToLoginPage()
                .enterTextIntoInputEmail(email)
                .enterTextIntoInputPassword(password)
                .clickOnSubmitButtonWithInvalidData()
                .checkTextInWarningMessage(warningMessage);
    }

    public Collection parametersForChangePasswordTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "changePassword";
        final boolean skipFirstRow = true;
        logger.info("Data file path: " + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }

    @After
    public void returnPassword() {
        loginAndGoToPersonalAreaPage(VALID_EMAIL, NEW_PASSWORD);
        pageProvider.personalAreaPage().clickOnLoginLink().checkIsRedirectToChangePasswordPage().enterTextIntoInputPassword(VALID_PASSWORD).enterTextIntoInputConfirmPasswordInput(VALID_PASSWORD).clickOnSubmitButton().checkTextInSuccessMessage(SUCCESS_MESSAGE).checkIsRedirectToPersonalAreaPage().getHeader().clickOnLogoutLink().checkIsRedirectToWelcomePage().getHeader().clickOnLoginLink().checkIsRedirectToLoginPage().enterTextIntoInputEmail(VALID_EMAIL).enterTextIntoInputPassword(VALID_PASSWORD).clickOnSubmitButton().checkIsRedirectToPersonalAreaPage().getHeader().checkIsLoginLinkNotVisible();
    }
}
