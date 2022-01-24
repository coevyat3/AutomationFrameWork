package buymeObjects;

import com.aventstack.extentreports.Status;
import helper.logger.LoggerHelper;
import helper.verification.VerificationHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.TestBase;

import java.util.List;



public class GiftCardPage extends TestBase {

    Logger log= LoggerHelper.getLogger(GiftCardPage.class);
    public GiftCardPage(){
        PageFactory.initElements(getDriver(),this);
    }
    @FindBy(css="h2.title")
    private WebElement giftCardHeader;

    @FindBy(css="input[inputmode]")
    private WebElement price;

    @FindBy(css=".grid.gifts-list button")
    private List<WebElement> giftCardPrices;

    @FindBy(css="div[class*=money]>button")
    private WebElement btn;

    @FindBy(css="div[class*=read-more]>span")
    private List<WebElement> readMore;

    @FindBy(css="li.parsley-required")
    private WebElement JSError;

    public String getGiftCardHeader(){
        log.info("Verify gift Card Page Header text " +giftCardHeader.getText());
        test.log(Status.INFO,"Verify Gift card page Header "+giftCardHeader.getText());
        return new VerificationHelper(getDriver()).getText(giftCardHeader);
    }
    public void enterAmount(String m){
        log.info("verify if Enter money Amount is Display ");
        test.log(Status.INFO,"verify if Enter money Amount is display");
        if (isPricePresent(price)) {
            price.sendKeys(m);
        }
        else {
            giftCardPrices.get(1).click();
        }
    }
    public WhoToSendPage insertAmount(String m){
        enterAmount(m);
        log.info("Click On Button"+btn.getAttribute("gtm"));
        test.log(Status.INFO,"Click on Button"+btn.getAttribute("gtm"));
        btn.click();
        return new WhoToSendPage();
    }
    public boolean isPricePresent(WebElement element){
        boolean flag=false;
        try{
            if(element.isDisplayed()){
                flag=true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }return  flag;
    }
    public void getReadMore(){
        for(WebElement element:readMore){
           element.click();
           new VerificationHelper(getDriver()).isDisplayed((WebElement) readMore);
        }
    }
    public boolean emptyMoneyInputCatchingJSError(){
        btn.click();
       return new VerificationHelper(getDriver()).isDisplayed(JSError);

    }


}
