package pages;

import org.openqa.selenium.WebDriver;
import pages.pageElements.TableMenu;
import parrentPage.ParentPage;

public class FavouritePage extends ParentPage {
    public TableMenu tableMenu;

    public FavouritePage(WebDriver webDriver) {
        super(webDriver, "favorites");
    }
}
