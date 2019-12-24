package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import pages.pageElements.LeftMenu;
import parrentPage.ParentPage;
import ru.yandex.qatools.htmlelements.element.Button;

public class BookCardPage extends ParentPage {
    public LeftMenu leftMenu;

    @FindBy(id = "SetBookFavStatus")
    private Button setSetBookFavStatus;

    public BookCardPage(WebDriver webDriver) {
        super(webDriver,  "");
    }

    public void clickSetBookFavStatus() {
        actionsWithOurElements.clickElement(setSetBookFavStatus);
    }
}
