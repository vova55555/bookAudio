package searchBookTest;

import abstractParrentTest.AbstractParentTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchBookFromPerformerPageTest extends AbstractParentTest {
    private By rowInTable;

    @Before
    public void getRowInTable(){
        rowInTable = performersPage.getRowInTable();
    }

    @Test
    public void checkSearchWithValidData() throws UnsupportedEncodingException {
        String performer = "Высоцкий Владимир";
        mainPage.openPage();
        mainPage.leftMenu.clickPerformersLink();
        performersPage.checkCurrentUrl();
        performersPage.rightMenu.typeTextIntoTheSearchField("Владимир Высоцкий");
        checkExpectedResult("Performers table is not displayed",
                performersPage.tableMenu.isAtLeastOneRowInTable(true, rowInTable));
        checkExpectedResult("Wrong amount of performers",
                1 == performersPage.tableMenu.countRowsInResultTable(rowInTable));
        checkExpectedResult("Wrong author was found",
                performer.equals(performersPage.tableMenu.getListOfResultsFromTable(rowInTable).get(0)));
        performersPage.clickShowAllBooksOfThePerformerLink(performer);
        findPage.checkCurrentUrl("/performer=" + URLEncoder.encode(performer, "UTF-8"));
        checkExpectedResult("Wrong performer was selected in filter",
                performer.equals(findPage.findRightMenu.getSelectedBookPerformer()));
        checkExpectedResult("Wrong amount of books",
                6 == findPage.tableMenu.countRowsInResultTable(findPage.getRowInTable()));
        String expectedBookResult = "Мартин Иден";
        checkExpectedResult(String.format("'%s' book is not present in result", expectedBookResult),
                findPage.tableMenu.isBookPresentInResult(expectedBookResult));
    }

    @Test
    public void checkSearchWithInvalidPerformer(){
        String invalidAuthor = "qwertyuqwertyuqwertyuu";
        performersPage.openPage();
        performersPage.rightMenu.typeTextIntoTheSearchField(invalidAuthor);
        checkExpectedResult("Performers table is displayed", !performersPage.tableMenu.isAtLeastOneRowInTable(false, rowInTable));
        String expectedText = String.format("По запросу «%s» ничего не найдено", invalidAuthor);
        checkExpectedResult("Wrong text about search results", expectedText.equals(performersPage.tableMenu.getSearchResultsText()));
    }
}
