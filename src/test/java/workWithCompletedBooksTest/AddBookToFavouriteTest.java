package workWithCompletedBooksTest;

import abstractParrentTest.AbstractParentTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

public class AddBookToFavouriteTest extends AbstractParentTest {
    private String author, book, login, password;
    private By rowInTable;

    @Before
    public void signUpNewUser(){
        author = "Шаламов Варлам";
        book = "Левый берег";
        login = user.getLogin();
        password = user.getPassword();
        apiManager.createNewAccount(login, password);
        rowInTable = findPage.getRowInTable();
    }

    @Ignore
    @Test
    public void checkAddingBookToCompleteTest(){
        authorizationPage.openPage();
        authorizationPage.enterLoginIntoLoginField(login);
        authorizationPage.enterPasswordIntoPasswordField(password);
        authorizationPage.clickLoginButton();
        mainPage.leftMenu.clickAuthorsLink();
        authorsPage.rightMenu.typeTextIntoTheSearchField(author);
        authorsPage.clickShowAllBooksOfTheAuthorLink(author);
        findPage.tableMenu.clickRowByName(book);
        bookCardPage.clickSetBookFavStatus();
        bookCardPage.leftMenu.clickFavouriteLink();
        checkExpectedResult("Wrong amount of favourite books",
                favouritePage.tableMenu.isBookPresentInResult(book));

    }
}
