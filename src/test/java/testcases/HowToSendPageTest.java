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
        navBarPage= new NavBarPage();
        searchResultPage= new SearchResultPage();
        giftCardPage= new GiftCardPage();
        whoToSendPage= new WhoToSendPage();
        howToSendPage= new HowToSendPage();
        loginPage= new LoginPage();
        searchResultPage= navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
        giftCardPage=searchResultPage.pickGiftCardByBusinessName(Constants.giftCardItem);
        whoToSendPage= giftCardPage.insertAmount(Constants.amount);
        howToSendPage= whoToSendPage.sendAll(Constants.FriendName,Constants.Bless,Constants.OwnBless, ResourceHelper.getResourcePath("src/main/resources/photos/flower.jpg"));
        loginPage= new LoginPage();

    }
    @Test(priority = 1,enabled = false)
    public void verifyHowToSendPageHeader(){
        AssertionHelper.verifyText(howToSendPage.getPageHeader(),Constants.howToSendPageHeader);
    }
    @Test(priority = 2,enabled = false)
    public void sendLaterOptionDateAndHour(){
        howToSendPage.setDate("יולי","13");
        howToSendPage.setHour("12:30");
        AssertionHelper.verifyTrue(howToSendPage.getWarnMsg());
    }
    @Test(priority = 3,enabled = false)
    public void selectSendByMail(){
        howToSendPage.setClickEmail();
        howToSendPage.setInsertEmail("a1@gmail.com");
    }
    @Test(priority = 4,enabled = false)
    public void setSenderName(){
        howToSendPage.setInsertName("aaaa");
    }
    @Test(priority = 5)
    public void setWhoToSendPage() throws InterruptedException {
        howToSendPage.sendAll("יולי","21","13:30","s1@walla.com","aaaaa");
        AssertionHelper.verifyTrue(loginPage.getLoginPageHeader().contains(Constants.loginHeader));
        Thread.sleep(4000);
    }


}
