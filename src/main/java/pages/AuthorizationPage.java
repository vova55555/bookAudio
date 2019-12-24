package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.pageElements.LeftMenu;
import parrentPage.ParentPage;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Form;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class AuthorizationPage extends ParentPage {
    public LeftMenu leftMenu;

    @FindBy(id = "authBlock")
    private Form authorizationForm;

    @FindBy(id = "login")
    private TextInput loginField;

    @FindBy(id = "pass")
    private TextInput passwordField;

    @FindBy(id = "doRegister")
    private Button noAccauntButton;

    @FindBy(xpath = "//input[@id='finish' and @value='Войти']")
    private Button loginButton;

    @FindBy(xpath = "//input[@id='finish' and @value='Зарегистрироваться']")
    private Button registerButton;
    private WebElement error;


    public AuthorizationPage(WebDriver webDriver) {
        super(webDriver, "auth");
    }


    public boolean isAuthorizationFormDisplayed() {
        return actionsWithOurElements.isElementDisplayed(authorizationForm);
    }

    public void enterLoginIntoLoginField(String login) {
        actionsWithOurElements.enterTextIntoInput(loginField, login);
    }

    public void enterPasswordIntoPasswordField(String password) {
        actionsWithOurElements.enterTextIntoInput(passwordField, password);
    }

    public void clickNoAccountButton(){
        actionsWithOurElements.clickElement(noAccauntButton);
    }

    public void clickRegisterButton() {
        actionsWithOurElements.clickElement(registerButton);
    }

    public String getAlertText() {
        return actionsWithOurElements.getAlertText();
    }

    public void clickLoginButton() {
        actionsWithOurElements.clickElement(loginButton);
    }
}
