package testcases;

import buymeObjects.*;
import com.aventstack.extentreports.Status;
import helper.logger.LoggerHelper;
import helper.resource.ResourceHelper;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import testBase.TestBase;
import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class RegistrationPageTest extends TestBase {
    Logger log= LoggerHelper.getLogger(RegistrationPageTest.class);
    String password=RandomStringUtils.random(11,true,true)+"X";
    String name=RandomStringUtils.random(8,true,false);
    String email=RandomStringUtils.random(6,true,true)+"@xyz.com";
    RegisterPage registerPage;
        LoginPage loginPage;
        NavBarPage navBarPage;
        SearchResultPage searchResultPage;
        GiftCardPage giftCardPage;
        WhoToSendPage whoToSendPage;
        HowToSendPage howToSendPage;



    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method,String browser) throws Exception {
        loadConfig(browser);
            registerPage= new RegisterPage(driver);
            navBarPage= new NavBarPage(driver);
            searchResultPage= new SearchResultPage(driver);
            giftCardPage= new GiftCardPage(driver);
            whoToSendPage= new WhoToSendPage(driver);
            howToSendPage= new HowToSendPage(driver);
            loginPage= new LoginPage(driver);
            searchResultPage= navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
            giftCardPage=searchResultPage.pickGiftCardByBusinessName(Constants.giftCardItem);
            whoToSendPage= giftCardPage.insertAmount(Constants.amount);
            howToSendPage= whoToSendPage.sendAll(Constants.FriendName,Constants.Bless,Constants.OwnBless, ResourceHelper.getResourcePath("src/main/resources/photos/flower.jpg"));
     //       loginPage=howToSendPage.sendAll(Constants.email,Constants.giftSender);
            registerPage=loginPage.gotoRegister();
            test.log(Status.INFO, method.getName()+"**************test started***************");
            log.info("**************"+method.getName()+"Started***************");


        }
    @Test
    public void testRegistration()  {
      //  AssertionHelper.verifyText(registerPage.getRegisterHeader(), Constants.registerHeader);
        registerPage.doRegistration(this.name,this.email,this.password,this.password);


    }



}
