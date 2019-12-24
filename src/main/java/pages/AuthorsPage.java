package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.pageElements.GeneralRightMenu;
import pages.pageElements.TableMenu;
import parrentPage.ParentPage;

public class AuthorsPage extends ParentPage {
    public GeneralRightMenu rightMenu;
    private By rowInTable= new By.ByXPath("//div[@class='title performersTitle']");
    public TableMenu tableMenu;

    public By getRowInTable() {
        return rowInTable;
    }

    public AuthorsPage(WebDriver webDriver) {
        super(webDriver, "authors");
    }

    public void clickShowAllBooksOfTheAuthorLink(String author) {
        String xpath = String.format("//a[@href='/find/author=%s']", author.replace(" ", "_"));
        tableMenu.clickAllBooksLink(xpath);
    }
}
