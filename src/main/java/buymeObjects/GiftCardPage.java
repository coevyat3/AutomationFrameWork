package buymeObjects;

import com.aventstack.extentreports.Status;
import helper.logger.LoggerHelper;
import helper.verification.VerificationHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static testBase.TestBase.test;

public class GiftCardPage {
    private WebDriver driver;
    Logger log= LoggerHelper.getLogger(GiftCardPage.class);
    public GiftCardPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="h2.title")
    private WebElement giftCardHeader;

    @FindBy(css="input[inputmode]")
    private WebElement price;

    @FindBy(css="div[class*=money]>button")
    private WebElement btn;

    public String getGiftCardHeader(){
        log.info("Verify gift Card Page Header text " +giftCardHeader.getText());
        test.log(Status.INFO,"Verify Gift card page Header "+giftCardHeader.getText());
        return new VerificationHelper(driver).getText(giftCardHeader);
    }
    public void enterAmount(String m){
        log.info("verify if Enter money Amount is Display ");
        test.log(Status.INFO,"verify if Enter money Amount is display");
        if(new VerificationHelper(driver).isDisplayed(price)){
            log.info( "Enter Money Amount " +m);
            test.log(Status.INFO, ": Enter money Amount "+m);
            price.sendKeys(m);

        }

    }
    public WhoToSendPage insertAmount(String m){
        enterAmount(m);
        log.info("Click On Button"+btn.getAttribute("gtm"));
        test.log(Status.INFO,"Click on Button"+btn.getAttribute("gtm"));
        btn.click();
        return new WhoToSendPage(driver);
    }


}
