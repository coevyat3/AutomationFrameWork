package helper.verification;

import helper.logger.LoggerHelper;
import testBase.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VerificationHelper {
    Logger log = LoggerHelper.getLogger(VerificationHelper.class);
    WebDriver driver;

    public VerificationHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            log.info("Element is present " + element);
            TestBase.logExtentReport("element is displayed"+element.getText());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Element is not present " + e.getCause());
            return false;
        }

    }

    public boolean isNotDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            log.info("Element is present " + element.getText());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Element is not present " + e.getCause());
            return true;
        }

    }

    public String getText(WebElement element) {
        if (element == null) {
            log.info("WebElement is Null..");
            return null;
        }
        boolean status = isDisplayed(element);
        if (status) {
            log.info("element text is: " + element.getText());
            return element.getText();
        } else {
            log.info("element text is not displayed");
            return null;
        }
    }
    public String getPageTitle(){
        return driver.getTitle();
    }
}