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

public class HowToSendPage {
    private WebDriver driver;
    Logger log= LoggerHelper.getLogger(WhoToSendPage.class);
    public HowToSendPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
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

    public LoginPage sendAll(String email,String name){
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
    public String getWhoToSendPageHeaderText(){
       return  new VerificationHelper(driver).getText(whoToSendPageHeader);
    }
    public boolean getWhoToSendPageHeader(){
        log.info("Verify who to send page Header display: "+whoToSendPageHeader.isDisplayed());
        test.log(Status.INFO,"Verify who to send Page header display "+whoToSendPageHeader.isDisplayed());
        return new VerificationHelper(driver).isDisplayed(whoToSendPageHeader);
    }
    public boolean checkNowBox(){
        return nowCheckBox.isSelected();
    }
}
