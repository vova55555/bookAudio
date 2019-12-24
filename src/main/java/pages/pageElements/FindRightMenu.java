package pages.pageElements;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.TextBlock;

public class FindRightMenu extends GeneralRightMenu {

    @FindBy(xpath = "//div[@class='bookAuthorFilter filterGroup']//div[@class='ms-sel-item ']")
    private TextBlock selectedBookAuthor;

    @FindBy(xpath = "//div[@data-length='1h']")
    private TextBlock lessThen1hDuration;

    @FindBy(xpath = "//div[@class='audioAuthorFilter filterGroup']//div[@class='ms-sel-item ']")
    private TextBlock selectedBookPerformer;

    public String getSelectedBookAuthor(){
        return actionsWithOurElements.getText(selectedBookAuthor);
    }

    public void select1hDuration() {
        actionsWithOurElements.clickElement(lessThen1hDuration);
        actionsWithOurElements.webDriverWait_10.until(ExpectedConditions.attributeToBe(lessThen1hDuration, "class", "active"));
    }

    public String getSelectedBookPerformer() {
        return actionsWithOurElements.getText(selectedBookPerformer);
    }
}
