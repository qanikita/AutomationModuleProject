package registratinTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelSpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTestsWithExcel extends BaseTest {

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTests(String email, String password, String firstname, String phone, String country, String region, String city, String expectedMessage) throws InterruptedException {
        pageProvider.welcomePage().openWelcomePage()
                .getHeader().clickOnRegistrationLink()
                .checkIsRedirectToRegistrationPage()
                .enterTextIntoInputEmail(email)
                .enterTextIntoInputPassword(password)
                .enterTextIntoInputFirstname(firstname)
                .enterTextIntoInputPhone(phone)
                .selectTextInCountryDropDown(country)
                .selectTextInRegionDropDown(region)
                .enterTextIntoInputCity(city)
                .clickOnContinueButton()
                .checkErrorsMessages(expectedMessage);
    }

    public Collection parametersForValidationMessagesTests() throws IOException {
        final String pathToDataFile = ConfigProvider.configProperties.DATA_FILE_PATH() + "testDataSuit.xls";
        final String sheetName = "registrationErrors";
        final boolean skipFirstRow = true;
        logger.info("Data file path: " + pathToDataFile + "\nSheet name: " + sheetName + "\nSkip first row: " + skipFirstRow);
        return new ExcelSpreadsheetData(new FileInputStream(pathToDataFile), sheetName, skipFirstRow).getData();
    }
}