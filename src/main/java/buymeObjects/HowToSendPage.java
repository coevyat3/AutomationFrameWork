package buymeObjects;

import com.aventstack.extentreports.Status;
import helper.logger.LoggerHelper;
import helper.verification.VerificationHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static testBase.TestBase.test;

public class HowToSendPage {
    private WebDriver driver;
    Logger log= LoggerHelper.getLogger(WhoToSendPage.class);
    public HowToSendPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
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
        return new VerificationHelper(driver).getText(header);
    }


    public LoginPage sendAll(String month,String day,String hour,String email,String name){
          setDate(month,day);
          setHour(hour);
          setClickEmail();
          setInsertEmail(email);
          setInsertName(name);
          clickBtn();
          return new LoginPage(driver);
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
        return nowCheckBox.isSelected();
    }
    public void setDate(String month,String day){
        laterCheckBox.click();
        clickDate.click();
        while(true){
            if(setMonth.getText().equalsIgnoreCase(month)){
                 break;
            }else{
                searchMonth.click();
            }

        }
        for(WebElement d:days){
            String str=d.getText();
            if(str.equalsIgnoreCase(day)){
                d.click();
                break;
            }
        }
    }
    public boolean getWarnMsg(){
       return new VerificationHelper(driver).isDisplayed(sendLaterWarnMsg);
    }
    public void setHour(String hour){
        clickHour.click();
        for(WebElement h:this.hour){
            if(h.getText().equalsIgnoreCase(hour)){
                h.click();
                break;
            }
        }
    }

}
