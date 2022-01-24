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



public class HowToSendPage extends TestBase {

    Logger log= LoggerHelper.getLogger(WhoToSendPage.class);
    public HowToSendPage(){

        PageFactory.initElements(getDriver(),this);
    }
   @FindBy(css = "div.step.active >div.label")
   private WebElement header;

    @FindBy(css = "svg[gtm*='email']")
    private WebElement clickEmail;

    @FindBy(css ="input#email" )
    private WebElement insertEmail;

    @FindBy(css = "input[maxlength]")
    private WebElement name;

    @FindBy(css = "div.mx-12>button")
    private WebElement btn;

    @FindBy(css=".active >.label")
    private WebElement whoToSendPageHeader;

    @FindBy(css="div[class*=selected]")
    private WebElement nowCheckBox;

    @FindBy(css="div[class*='button-later']")
    private WebElement laterCheckBox;

    @FindBy(css="div.display-date")
    private WebElement clickDate;

    @FindBy(css="span.selected-month-name")
    private WebElement setMonth;

    @FindBy(css = "span.selected-text")
    private WebElement clickHour;

    @FindBy(css="div.dropdown ul[style] li")
    private List<WebElement> hour;


    @FindBy(css="svg.icon.chevron_next_rtl")
    private WebElement searchMonth;

    @FindBy(css="span.ember-view.bm-date-day:not(.disabled)")
    private List<WebElement> days;

    @FindBy(css="span.notification")
    private WebElement sendLaterWarnMsg;


    public String getPageHeader(){
        return new VerificationHelper(getDriver()).getText(header);
    }


    public LoginPage sendAll(String month,String day,String hour,String email,String name){
          setDate(month,day);
          setHour(hour);
          setClickEmail();
          setInsertEmail(email);
          setInsertName(name);
          clickBtn();
          return new LoginPage();
    }

    public void setClickEmail(){
        log.info("Pick Send with email");
        test.log(Status.INFO,"Pick send with email");
        clickEmail.click();
    }
    public void setInsertEmail(String txt){
        log.info("Insert Email.. "+txt);
        test.log(Status.INFO,"Insert Email.. "+txt);
        insertEmail.sendKeys(txt);
    }
    public void setInsertName(String name){
        log.info("Set Sender Name: "+name);
        test.log(Status.INFO,"Set sender name: "+name);
        this.name.sendKeys(name);
    }
    public void clickBtn(){
        log.info("Click Button "+btn.getAttribute("gtm"));
        test.log(Status.INFO,"Click Button "+btn.getAttribute("gtm"));
        btn.click();
    }

    public boolean checkNowBox(){
        return new VerificationHelper(getDriver()).isSelected(nowCheckBox);
    }
    public void setDate(String month,String day){
        log.info(" Click on Sent gift later");
        test.log(Status.INFO,"Click on sent Gift Later");
        laterCheckBox.click();
        clickDate.click();
        while(true){
            if(setMonth.getText().equalsIgnoreCase(month)){
                 break;
            }else{
                searchMonth.click();
            }

        }
        log.info("set date to month: "+month +" Day: "+day);
        test.log(Status.INFO,"Set month to: "+month+" Set day to: "+day);

        for(WebElement d:days){
            String str=d.getText();
            if(str.equalsIgnoreCase(day)){
                d.click();
                break;
            }
        }

    }
    public boolean getWarnMsg(){
       return new VerificationHelper(getDriver()).isDisplayed(sendLaterWarnMsg);
    }
    public void setHour(String hour){
        clickHour.click();
        log.info("Set hour to: "+hour);
        test.log(Status.INFO,"set hour to: "+hour);
        for(WebElement h:this.hour){
            if(h.getText().equalsIgnoreCase(hour)){
                h.click();
                break;
            }
        }

    }

}
