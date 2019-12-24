package authorizationTest;

import abstractParrentTest.AbstractParentTest;
import org.junit.Before;
import org.junit.Test;

public class LoginTest extends AbstractParentTest {

    private String login;
    private String password;

    @Before
    public void createUser() {
        login = user.getLogin();
        password = user.getPassword();
        apiManager.createNewAccount(login, password);
    }

    @Test
    public void checkLoginWithValidData() {
        mainPage.openPage();
        mainPage.leftMenu.clickAuthorizationLink();
        authorizationPage.checkCurrentUrl();
        checkExpectedResult("Authorization form is not displayed",
                authorizationPage.isAuthorizationFormDisplayed());
        authorizationPage.enterLoginIntoLoginField(login);
        authorizationPage.enterPasswordIntoPasswordField(password);
        authorizationPage.clickLoginButton();
        mainPage.checkCurrentUrl();
        checkExpectedResult("Incorrect user is logged in",
                openProfilePageAndGetCurrentUser().equals(user.getLogin()));
    }

    @Test
    public void checkLoginWithInvalidPassword(){
        authorizationPage.openPage();
        checkExpectedResult("Authorization form is not displayed",
                authorizationPage.isAuthorizationFormDisplayed());
        authorizationPage.enterLoginIntoLoginField(login);
        authorizationPage.enterPasswordIntoPasswordField("any text");
        authorizationPage.clickLoginButton();
        authorizationPage.checkCurrentUrl();
        checkExpectedResult("Wrong error message is displayed",
                "Неверный логин или пароль".equals(authorizationPage.getAlertText()));
    }

}