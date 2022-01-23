package buymeObjects;

import com.aventstack.extentreports.Status;
import helper.browserConfiguration.config.ObjectReader;
import helper.logger.LoggerHelper;
import helper.verification.VerificationHelper;
import helper.wait.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static testBase.TestBase.test;

public class PaymentPage {
    WebDriver driver;
    Logger log= LoggerHelper.getLogger(PaymentPage.class);
    public PaymentPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="div.total-price")
    private WebElement pageHeader;


    public boolean getPageTitleText(){
        log.info("Verify Payment Page title "+pageHeader.getText());
        test.log(Status.INFO,"Verify Payment page title "+pageHeader.getText());
        new WaitHelper(driver).waitForElement(pageHeader, ObjectReader.reader.getExplicitWait());
        return new VerificationHelper(driver).isDisplayed(pageHeader);
    }

}
