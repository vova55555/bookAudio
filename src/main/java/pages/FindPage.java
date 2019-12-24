package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.pageElements.FindRightMenu;
import pages.pageElements.TableMenu;
import parrentPage.ParentPage;

public class FindPage extends ParentPage {
    public FindRightMenu findRightMenu;
    private By rowInTable = new By.ByXPath("//div[@class='titleContent']");
    public TableMenu tableMenu;

    public By getRowInTable() {
        return rowInTable;
    }

    public FindPage(WebDriver webDriver) {
        super(webDriver, "find");
    }

    public void checkCurrentUrl(String searchRequest)  {
        expectedUrl = expectedUrl + searchRequest.replace("+", "_");
        try {
            actionsWithOurElements.waitForUrlIsChangedToExpected(expectedUrl );
            Assert.assertEquals("URL is not expected", expectedUrl, webDriver.getCurrentUrl());
        } catch (Exception e) {
            logger.error("Cannot get url " + e);
            Assert.fail("Cannot get url " + e);
        }
    }
}
