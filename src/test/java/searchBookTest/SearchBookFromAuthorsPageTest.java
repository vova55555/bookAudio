package searchBookTest;

import abstractParrentTest.AbstractParentTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchBookFromAuthorsPageTest extends AbstractParentTest {
    private By rowInTable;

    @Before
    public void getRowInTable(){
        rowInTable = authorsPage.getRowInTable();
    }

    @Test
    public void checkSearchWithValidData() throws UnsupportedEncodingException {
        String author = "Яковлев Юрий";
        mainPage.openPage();
        mainPage.leftMenu.clickAuthorsLink();
        authorsPage.checkCurrentUrl();
        authorsPage.rightMenu.typeTextIntoTheSearchField("Юрий Яковлев");
        checkExpectedResult("Authors table is not displayed",
                authorsPage.tableMenu.isAtLeastOneRowInTable(true, rowInTable));
        checkExpectedResult("Wrong amount of authors",
                1 == authorsPage.tableMenu.countRowsInResultTable(rowInTable));
        checkExpectedResult("Wrong author was found",
                author.equals(authorsPage.tableMenu.getFirstResultFromTable(rowInTable)));
        authorsPage.clickShowAllBooksOfTheAuthorLink(author);
        findPage.checkCurrentUrl("/author=" + URLEncoder.encode(author, "UTF-8"));
        checkExpectedResult("Wrong author was selected in filter",
                author.equals(findPage.findRightMenu.getSelectedBookAuthor()));
        checkExpectedResult("Wrong amount of books",
                8 == findPage.tableMenu.countRowsInResultTable(findPage.getRowInTable()));
        String expectedBookResult = "Мальчик с коньками";
        checkExpectedResult(String.format("'%s' book is not present in result", expectedBookResult),
                findPage.tableMenu.isBookPresentInResult(expectedBookResult));
    }

    @Test
    public void checkSearchWithInvalidAuthor(){
        String invalidAuthor = "qwertyuqwertyuqwertyuu";
        authorsPage.openPage();
        authorsPage.rightMenu.typeTextIntoTheSearchField(invalidAuthor);
        checkExpectedResult("Authors table is displayed",
                !authorsPage.tableMenu.isAtLeastOneRowInTable(false, rowInTable));
        String expectedText = String.format("По запросу «%s» ничего не найдено", invalidAuthor);
        checkExpectedResult("Wrong text about search results",
                expectedText.equals(authorsPage.tableMenu.getSearchResultsText()));
    }
}
