package parrentPage;

import libs.ActionsWithOurElements;
import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.pageElements.WebDriverAwareDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class ParentPage {
    protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected ActionsWithOurElements actionsWithOurElements;
    protected String expectedUrl;

    public ParentPage(WebDriver webDriver, String partUrl) {
        this.webDriver = webDriver;
        ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
        PageFactory.initElements(new WebDriverAwareDecorator(new HtmlElementLocatorFactory(webDriver), webDriver), this);
        actionsWithOurElements = new ActionsWithOurElements(webDriver);
        expectedUrl = configProperties.base_url() + partUrl;
    }

    public void openPage() {
        try {
            webDriver.get(expectedUrl);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Can not work with browser");
        }
    }

    public void checkCurrentUrl() {
        try {
            actionsWithOurElements.waitForUrlIsChangedToExpected(expectedUrl);
            Assert.assertEquals("URL is not expected", expectedUrl, webDriver.getCurrentUrl());
        } catch (Exception e) {
            logger.error("Cannot get url " + e);
            Assert.fail("Cannot get url " + e);
        }
    }
}