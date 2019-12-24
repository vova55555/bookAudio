package abstractParrentTest;

import DTO.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import libs.ApiManager;
import libs.ConfigProperties;
import libs.Utils;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pages.*;

import java.util.concurrent.TimeUnit;

public class AbstractParentTest {
    protected User user;
    private WebDriver webDriver;
    protected AuthorizationPage authorizationPage;
    protected MainPage mainPage;
    protected AuthorsPage authorsPage;
    protected PerformersPage performersPage;
    private ProfilePage profilePage;
    protected ApiManager apiManager;
    protected FindPage findPage;
    protected BookCardPage bookCardPage;
    protected FavouritePage favouritePage;

    @Before
    public void setUp() throws Exception {
        user = new User("UniqueLogin" + Utils.getDateAndTimeFormatted());
        webDriver = driverInit();
        authorizationPage = new AuthorizationPage(webDriver);
        mainPage = new MainPage(webDriver);
        profilePage = new ProfilePage(webDriver);
        authorsPage = new AuthorsPage(webDriver);
        performersPage = new PerformersPage(webDriver);
        findPage = new FindPage(webDriver);
        bookCardPage = new BookCardPage(webDriver);
        favouritePage = new FavouritePage(webDriver);
        ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(configProperties.TIME_FOR_DEFAULT_WAIT(), TimeUnit.SECONDS);
        apiManager = new ApiManager(configProperties);
    }

    private WebDriver driverInit() throws Exception {
        String browser = System.getProperty("browser");
        if ((browser == null) || ("chrome".equalsIgnoreCase(browser))) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if ("ie".equalsIgnoreCase(browser)) {
            WebDriverManager.iedriver().setup();
            return new InternetExplorerDriver();
        } else {
            throw new Exception("Check browser var");
        }
    }

    protected String openProfilePageAndGetCurrentUser() {
        profilePage.openPage();
        return profilePage.getUserName();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    protected void checkExpectedResult(String message, boolean actualResult) {
        Assert.assertEquals(message, true, actualResult);
    }
}
