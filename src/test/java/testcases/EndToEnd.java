package testcases;

import buymeObjects.*;
import helper.assertion.AssertionHelper;
import helper.resource.ResourceHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.lang.reflect.Method;

public class EndToEnd extends TestBase {
    LoginPage loginPage;
    NavBarPage navBarPage;
    SearchResultPage searchResultPage;
    GiftCardPage giftCardPage;
    WhoToSendPage whoToSendPage;
    HowToSendPage howToSendPage;
    HomePage homePage;
    PaymentPage paymentPage;


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
        homePage= new HomePage(driver);
        paymentPage=new PaymentPage(driver);

    }
    @Test
    public void testProcess(){
        searchResultPage= navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
        giftCardPage=searchResultPage.pickGiftCardByBusinessName(Constants.giftCardItem);
        whoToSendPage= giftCardPage.insertAmount(Constants.amount);
        howToSendPage= whoToSendPage.sendAll(Constants.FriendName,Constants.Bless,Constants.OwnBless, ResourceHelper.getResourcePath("src/main/resources/photos/flower.jpg"));
        loginPage= howToSendPage.sendAll("יולי","21","13:30","s1@walla.com","aaaaa");
        paymentPage=loginPage.login(Constants.email,Constants.password);
        AssertionHelper.verifyTrue(paymentPage.getPageTitleText());
    }
}
