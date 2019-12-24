package pages.pageElements;

import libs.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import java.util.ArrayList;
import java.util.List;

@FindBy(xpath = "//div[@class='cardTitles ']")
public class TableMenu extends CommonActionsWithElements{

    @FindBy(xpath = "//div[@class='cardTitles ']")
    private WebElement resultTitle;


    @FindBy(xpath = "//div[@class='status']")
    private TextBlock searchResultsText;

    public boolean isAtLeastOneRowInTable(boolean doWeExpectVisibility, By rowInTableBy) {
        ExpectedCondition expectedCondition = (doWeExpectVisibility) ?
                ExpectedConditions.numberOfElementsToBeMoreThan(rowInTableBy, 0) :
                ExpectedConditions.numberOfElementsToBe(rowInTableBy, 0);
        actionsWithOurElements.webDriverWait_10.until(expectedCondition);
        return actionsWithOurElements.isElementDisplayed(rowInTableBy);
    }

    public int countRowsInResultTable(By rowInTable) {
        Utils.waitABit(4000);
        return actionsWithOurElements.countElements(rowInTable);
    }

    public List<String> getListOfResultsFromTable(By rowInTableBy) {
        List<String> results = new ArrayList<>();
        for (WebElement rowInTable : webDriver.findElements(rowInTableBy)) {
            results.add(rowInTable.getText().split("\\n")[0]);
        }
        return results;
    }

    public String getFirstResultFromTable(By rowInTableBy){
        return getListOfResultsFromTable(rowInTableBy).get(0);
    }

    public String getSearchResultsText() {
        return actionsWithOurElements.getText(searchResultsText);
    }

    public void clickAllBooksLink(String xpath) {
        WebElement showAllBooksOfTheAuthorLink = webDriver.findElement(By.xpath(xpath));
        actionsWithOurElements.clickElement(showAllBooksOfTheAuthorLink);
    }
    public boolean isBookPresentInResult(String expectedBookResultName) {
        try {
            return actionsWithOurElements.isElementDisplayed(getBookElementByName(expectedBookResultName));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private WebElement getBookElementByName(String expectedBookResultName) {
        String xpath = String.format("//div[@class='topLineWrap' and contains(., '%s')]/../..", expectedBookResultName);
        return webDriver.findElement(new By.ByXPath(xpath));
    }

    public void clickRowByName(String bookName) {
        actionsWithOurElements.clickElement(getBookElementByName(bookName));
    }
}
