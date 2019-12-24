package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.pageElements.GeneralRightMenu;
import pages.pageElements.TableMenu;
import parrentPage.ParentPage;

public class PerformersPage extends ParentPage {
    public GeneralRightMenu rightMenu;
    private By rowInTable = new By.ByXPath("//div[@class='title performersTitle']");
    public TableMenu tableMenu;

    public By getRowInTable() {
        return rowInTable;
    }

    public PerformersPage(WebDriver webDriver) {
        super(webDriver, "performers");
    }

    public void clickShowAllBooksOfThePerformerLink(String performer) {
        String xpath = String.format("//a[@href='/find/performer=%s']", performer.replace(" ", "_"));
        tableMenu.clickAllBooksLink(xpath);
    }
}
