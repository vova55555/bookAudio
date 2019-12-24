package pages;

import org.openqa.selenium.WebDriver;
import pages.pageElements.LeftMenu;
import parrentPage.ParentPage;

public class MainPage extends ParentPage {
    public LeftMenu leftMenu;

    public MainPage(WebDriver webDriver) {
        super(webDriver, "");
    }


}
