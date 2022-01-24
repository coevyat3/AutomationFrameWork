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
import testBase.TestBase;

import static testBase.TestBase.test;

public class LoginPage extends TestBase {

    private Logger log= LoggerHelper.getLogger(LoginPage.class);
    WaitHelper waitHelper;

    public LoginPage(){

        PageFactory.initElements(getDriver(),this);


    }
    @FindBy(css = "div[class*='oldschool'] input[type='email']")
    private WebElement email;

    @FindBy(css = "div[class*='oldschool'] input[type='password']")
    private WebElement password;

    @FindBy(css = "div[class*='login']+button")
    private WebElement loginBtn;

    @FindBy(css = "div[class*='reg'] span.text-link")
    private WebElement registerBtn;

    @FindBy(css = "div[class*='login-form'] h1.bm-h1")
    private WebElement loginPageHeader;


    @FindBy(css="div.step.active>div:nth-child(1)")
    private WebElement paymentPageHeader;


    public HomePage doLogin(String email,String password){
               setEmail(email);
               setPassword(password);
               clickLoginBtn();
              return new HomePage();
    }
    public void setEmail(String email){
       this.email.sendKeys(email);
    }
    public void setPassword(String password){
        this.password.sendKeys(password);
    }
    public void clickLoginBtn(){
        loginBtn.click();
    }
    public RegisterPage gotoRegister(){
        log.info("Go To Register Page");
        test.log(Status.INFO,"Go to register Page");
        clickRegisterBtn();
        return  new RegisterPage();
    }
    public void clickRegisterBtn(){
        registerBtn.click();
    }

    public String getLoginPageHeader(){
      new  WaitHelper(getDriver()).waitForElement(loginPageHeader,ObjectReader.reader.getExplicitWait(),50);
        return new VerificationHelper(getDriver()).getText(loginPageHeader);
    }
    public PaymentPage login(String email,String password){
        log.info("Set email to: "+email);
        test.log(Status.INFO,"Set password to: "+password);
        setEmail(email);
        setPassword(password);
        clickLoginBtn();
        return new PaymentPage();
    }




}
