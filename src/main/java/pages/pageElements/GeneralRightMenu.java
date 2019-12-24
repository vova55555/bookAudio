package pages.pageElements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.TextInput;

@FindBy(id = "rightColumn")
public class GeneralRightMenu extends CommonActionsWithElements{

    @FindBy(xpath = "//input[@class='searchField']")
    private TextInput searchField;

    public void typeTextIntoTheSearchField(String text) {
        actionsWithOurElements.enterTextIntoInput(searchField, text);
    }
}
