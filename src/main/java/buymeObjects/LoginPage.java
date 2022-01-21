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

import static testBase.TestBase.test;

public class LoginPage {
    private WebDriver driver;
    private Logger log= LoggerHelper.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(css = "div[class*='oldschool'] input[type='email']")
    private WebElement email;

    @FindBy(css = "div[class*='oldschool'] input[type='password']")
    private WebElement password;

    @FindBy(css = "div[class*='login']+button")
    private WebElement loginBtn;

    @FindBy(css = "div[class*='reg'] span.text-link")
    private WebElement registerBtn;

    @FindBy(css = "h1.bm-h1")
    private WebElement loginPageHeader;


    public HomePage doLogin(String email,String password){
               setEmail(email);
               setPassword(password);
               clickLoginBtn();
               return new HomePage(driver);
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
        return  new RegisterPage(driver);
    }
    public void clickRegisterBtn(){
        registerBtn.click();
    }
    public String getLoginPageHeaderText(){
        return new VerificationHelper(driver).getText(loginPageHeader);
    }
    public boolean getLoginPageHeader(){
        return new VerificationHelper(driver).isDisplayed(loginPageHeader);
    }




}
