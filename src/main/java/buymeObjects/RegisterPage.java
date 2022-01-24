package buymeObjects;

import com.aventstack.extentreports.Status;
import helper.logger.LoggerHelper;
import helper.wait.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.TestBase;

import static testBase.TestBase.test;

public class RegisterPage extends TestBase {

    private Logger log= LoggerHelper.getLogger(HomePage.class);

    public RegisterPage(){
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "form[action='register'] input[type*='text']")
    private WebElement name;

    @FindBy(css = "form[action='register'] input[type*='email']")
    private WebElement email;

    @FindBy(id="valPass")
    private WebElement password;

    @FindBy(css="input[data-parsley-equalto]")
    private WebElement cpw;

    @FindBy(css="div[class*='field']+button[type=submit] ")
    private WebElement registerBtn;

    @FindBy(css="h1.bm-h1")
    private WebElement registerPageHeader;

    public void setName(String name){
        this.name.sendKeys(name);
    }
    public void setPassword(String password){
        this.password.sendKeys(password);
    }
    public void setEmail(String email){
        this.email.sendKeys(email);
    }
    public void setCpw(String cpw){
        this.cpw.sendKeys(cpw);
    }
    public HomePage doRegistration(String name,String email,String password,String cpw){
        log.info("Doing Registration...");
        test.log(Status.INFO,"Doing Registration...");
        log.info("Set name to: "+name);
        test.log(Status.INFO,"Set name to: "+name);
        setName(name);
        log.info("Set Email to: "+email);
        test.log(Status.INFO,"Set email to: "+email);
        setEmail(email);
        log.info("Set password to: "+password);
        test.log(Status.INFO,"Set password to: "+password);
        setPassword(password);
        log.info("Confirm password: "+password);
        test.log(Status.INFO,"conform password: "+password);
        setCpw(cpw);
        log.info("Click Button: "+registerBtn.getAttribute("gtm"));
        test.log(Status.INFO,"Click Button: "+registerBtn.getAttribute("gtm"));
        registerBtn.click();
        return new HomePage();

    }
    public String getRegisterHeader(){

        return registerPageHeader.getText();
    }



}
