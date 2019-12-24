package searchBookTest;

import abstractParrentTest.AbstractParentTest;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchBookFromFindPageTest extends AbstractParentTest {

    @Test
    public void checkSearchWithValidData() throws UnsupportedEncodingException {
        String author = "Томпсон";
        String book = "Снап";
        mainPage.openPage();
        mainPage.leftMenu.clickFindLink();
        findPage.checkCurrentUrl();
        findPage.findRightMenu.typeTextIntoTheSearchField(author);
        findPage.checkCurrentUrl("/search=" + URLEncoder.encode(author, "UTF-8"));
        assertAmountOfFilteredResultsAndCheckExpectedBook(24, book);
        findPage.findRightMenu.select1hDuration();
        assertAmountOfFilteredResultsAndCheckExpectedBook(6, book);
        findPage.findRightMenu.typeTextIntoTheSearchField(author + " " + book);
        assertAmountOfFilteredResultsAndCheckExpectedBook(1, book);
    }

    private void assertAmountOfFilteredResultsAndCheckExpectedBook(int expectedAmount, String expectedBook){
        checkExpectedResult("Wrong amount of authors",
                expectedAmount == findPage.tableMenu.countRowsInResultTable(findPage.getRowInTable()));
        checkExpectedResult("Expected book is not present in results",
                findPage.tableMenu.isBookPresentInResult(expectedBook));
    }
}
