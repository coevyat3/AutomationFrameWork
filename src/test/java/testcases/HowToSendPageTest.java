package testcases;

import helper.assertion.AssertionHelper;
import helper.resource.ResourceHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testBase.TestBase;
import buymeObjects.*;

import java.lang.reflect.Method;

public class HowToSendPageTest extends TestBase {

    NavBarPage navBarPage;
    SearchResultPage searchResultPage;
    GiftCardPage giftCardPage;
    WhoToSendPage whoToSendPage;
    HowToSendPage howToSendPage;
    LoginPage loginPage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, String browser) throws Exception {
        loadConfig(browser);
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

    }
    @Test(priority = 3)
    public void sendAll(){
        howToSendPage.sendAll(Constants.email,Constants.giftSender);
        AssertionHelper.verifyTrue(loginPage.getLoginPageHeader());
    }

}
