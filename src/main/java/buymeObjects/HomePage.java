package buymeObjects;

import com.aventstack.extentreports.Status;
import helper.logger.LoggerHelper;
import helper.verification.VerificationHelper;
import helper.wait.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.TestBase;

import static testBase.TestBase.test;

public class HomePage {
    private WebDriver driver;
    private Logger log= LoggerHelper.getLogger(HomePage.class);

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css ="li[class='notSigned'] a" )
    private WebElement loginBtn;

    @FindBy(css = "div[class*='nav'] img")
    private WebElement logoImg;


    public LoginPage gotoLogin(){
         loginBtn.click();
         return new LoginPage(driver);
    }
    public String getHomePageTitle(){
        log.info("Checking HomePage title");
        test.log(Status.INFO,"Checking HomePage title");
       return  new VerificationHelper(driver).getPageTitle();

    }
    public boolean isLogoDisplay(){
        log.info("Checking logoImg Display "+logoImg);
        test.log(Status.INFO,"Checking logo img display ");
        return new VerificationHelper(driver).isDisplayed(logoImg);
    }
    public String getSiteUrl(){
        log.info("Return homePage URL");
        test.log(Status.INFO,"Return HomePage URL");
        return driver.getCurrentUrl();
    }




}
