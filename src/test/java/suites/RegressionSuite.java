package suites;

import authorizationTest.LoginTest;
import authorizationTest.SignUpNewUserTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import searchBookTest.SearchBookFromAuthorsPageTest;
import searchBookTest.SearchBookFromFindPageTest;
import searchBookTest.SearchBookFromPerformerPageTest;
import workWithCompletedBooksTest.AddBookToFavouriteTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                LoginTest.class,
                SignUpNewUserTest.class,
                SearchBookFromAuthorsPageTest.class,
                SearchBookFromFindPageTest.class,
                SearchBookFromPerformerPageTest.class,
                AddBookToFavouriteTest.class
        }
)
public class RegressionSuite {
}