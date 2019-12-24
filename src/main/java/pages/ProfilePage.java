package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import parrentPage.ParentPage;

public class ProfilePage extends ParentPage {

    @FindBy(tagName = "h2")
    private WebElement login;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver, "profile");
    }

    public String getUserName() {
        return actionsWithOurElements.getText(login);
    }
}
