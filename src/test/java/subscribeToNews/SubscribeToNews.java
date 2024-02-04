package subscribeToNews;

import baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

public class SubscribeToNews extends BaseTest {

    String SUCCESS_MESSAGE = "Ваша підписка успішно оновлена!";

    @Test
    public void acceptSubscribe() {
        pageProvider.loginPagePage().openLoginPageAndFillLoginFormWithValidCred()
                .clickOnSubscribeLink()
                .checkIsRedirectToSubscribePage()
                .acceptOrDeclineSubscription("yes")
                .clickOnSubmitButton()
                .checkTextInSuccessMessage(SUCCESS_MESSAGE)
                .checkIsRedirectToPersonalAreaPage();
    }

    @After
    public void declineSubscribe() {
        pageProvider.personalAreaPage().clickOnSubscribeLink()
                .checkIsRedirectToSubscribePage()
                .acceptOrDeclineSubscription("no")
                .clickOnSubmitButton()
                .checkTextInSuccessMessage(SUCCESS_MESSAGE)
                .checkIsRedirectToPersonalAreaPage();
    }
}
