package loginTests;

import baseTest.BaseTest;
import org.junit.Test;

import static data.TestData.VALID_EMAIL;
import static data.TestData.VALID_PASSWORD;


public class LoginTests extends BaseTest {

    @Test
    public void validLogin() {
        pageProvider.welcomePage().openWelcomePage()
                .checkIsRedirectToWelcomePage().
                getHeader().clickOnLoginLink()
                .checkIsRedirectToLoginPage()
                .enterTextIntoInputEmail(VALID_EMAIL)
                .enterTextIntoInputPassword(VALID_PASSWORD)
                .clickOnSubmitButton()
                .checkIsRedirectToPersonalAreaPage()
                .getHeader().checkIsLoginLinkNotVisible();
    }
}
