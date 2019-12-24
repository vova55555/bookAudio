package authorizationTest;

import abstractParrentTest.AbstractParentTest;
import org.junit.Test;

public class SignUpNewUserTest extends AbstractParentTest {

    @Test
    public void registerUniqueUser(){
        mainPage.openPage();
        mainPage.leftMenu.clickAuthorizationLink();
        authorizationPage.checkCurrentUrl();
        checkExpectedResult("Authorization form is not displayed",
                authorizationPage.isAuthorizationFormDisplayed());
        authorizationPage.clickNoAccountButton();
        authorizationPage.enterLoginIntoLoginField(user.getLogin());
        authorizationPage.enterPasswordIntoPasswordField(user.getPassword());
        authorizationPage.clickRegisterButton();
        mainPage.checkCurrentUrl();
        checkExpectedResult("Incorrect user is logged in",
                openProfilePageAndGetCurrentUser().equals(user.getLogin()));
    }

    @Test
    public void createUserWithDuplicatedLogin() {
        String login = user.getLogin();
        String password = user.getPassword();
        apiManager.createNewAccount(login, password);
        executeSignUpWithInvalidData(login, password, "Ник уже занят");
    }

    @Test
    public void createUserWithoutLogin(){
        executeSignUpWithInvalidData("", user.getPassword(),
                "Ник должен быть латинскими буквами без пробелов и спец символов");
    }

    @Test
    public void createUserWithoutPassword(){
        executeSignUpWithInvalidData(user.getLogin(), "",
                "Длина пароля не меньше 6 символов");
    }

    private void executeSignUpWithInvalidData(String invalidLogin, String invalidPassword, String expectedError){
        authorizationPage.openPage();
        authorizationPage.clickNoAccountButton();
        authorizationPage.enterLoginIntoLoginField(invalidLogin);
        authorizationPage.enterPasswordIntoPasswordField(invalidPassword);
        authorizationPage.clickRegisterButton();
        checkExpectedResult("Wrong error message is displayed",
                expectedError.equals(authorizationPage.getAlertText()));
    }
}
