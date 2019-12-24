package libs;

import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class ActionsWithOurElements {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    public WebDriverWait webDriverWait_10;

    public ActionsWithOurElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
        this.webDriverWait_10 = new WebDriverWait(webDriver, configProperties.TIME_FOR_EXPLICIT_WAIT_LOW());
    }

    public void enterTextIntoInput(WebElement webElement, String text) {
        try {
            webDriverWait_10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered into " + getElementName(webElement) + "input.");
        } catch (Exception e) {
            stopTestAndPrintMessage();
        }
    }

    public void clickElement(WebElement webElement) {
        try {
            webDriverWait_10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element " + getElementName(webElement) + "was clicked.");
        } catch (Exception e) {
            stopTestAndPrintMessage();
        }
    }

    private String getElementName(WebElement webElement) {
        return (webElement instanceof TypifiedElement) ? "'" + ((TypifiedElement) webElement).getName() + "'" + " " : "";
    }

    public boolean isElementDisplayed(WebElement webElement) {
        String element = (webElement instanceof TypifiedElement) ? " '" + ((TypifiedElement) webElement).getName() + "'" : "";
        String elementIsNotDisplayedMessage = String.format("Element%s is not displayed", element);
        try {
            boolean state = webElement.isDisplayed();
            String message = state ? String.format("Element%s is displayed", element) : elementIsNotDisplayedMessage;
            logger.info(message);
            return state;
        } catch (Exception e) {
            logger.info(elementIsNotDisplayedMessage);
            return false;
        }
    }

    public boolean isElementDisplayed(By xpath) {
        try {
            return isElementDisplayed(webDriver.findElement(xpath));
        } catch (Exception e) {
            return false;
        }
    }

    private void stopTestAndPrintMessage() {
        String errorText = "Can not work with element ";
        logger.error(errorText);
        Assert.fail(errorText);
    }

    public String getText(WebElement webElement) {
        logger.info("Get text of the element");
        return webElement.getText();
    }

    public void waitForUrlIsChangedToExpected(String expectedUrl) {
        webDriverWait_10.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    public String getAlertText() {
        webDriverWait_10.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();
        return alert.getText();
    }

    public int countElements(By rowInTable) {
        int amount = 0;
        try {
            amount = webDriver.findElements(rowInTable).size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amount;
    }
}
