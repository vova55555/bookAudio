package pages.pageElements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Link;

@FindBy(id = "leftColumnFixed")
public class LeftMenu extends CommonActionsWithElements {

    @FindBy(xpath = "//a[@original-title='Найти аудиокнигу']")
    private Link findBook;

    @FindBy(xpath = "//a[@original-title='Авторизация']")
    private Link authorization;

    @FindBy(xpath = "//i[@class='fa fa-pencil']")
    private Link authors;

    @FindBy(xpath = "//a[@original-title='Исполнители']")
    private Link performersLink;

    @FindBy(xpath = "//a[@original-title='Избранное']")
    private Link favouriteLink;


    public void clickAuthorizationLink() {
        actionsWithOurElements.clickElement(authorization);
    }

    public void clickAuthorsLink() {
        actionsWithOurElements.clickElement(authors);
    }

    public void clickFindLink() {
        actionsWithOurElements.clickElement(findBook);
    }

    public void clickPerformersLink() {
        actionsWithOurElements.clickElement(performersLink);
    }

    public void clickFavouriteLink() {
        actionsWithOurElements.clickElement(favouriteLink);
    }
}