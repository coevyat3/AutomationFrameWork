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
        homePage= new HomePage();
        navBarPage= new NavBarPage();
        searchResultPage= new SearchResultPage();
        giftCardPage= new GiftCardPage();
        whoToSendPage= new WhoToSendPage();
        howToSendPage= new HowToSendPage();
        loginPage= new LoginPage();
        homePage= new HomePage();
        paymentPage=new PaymentPage();

    }
    @Test
    public void testProcess(){
        AssertionHelper.verifyText(homePage.getSiteUrl(),Constants.URL);
        searchResultPage= navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
        AssertionHelper.verifyTrue(searchResultPage.getSearchResultHeader());
        giftCardPage=searchResultPage.pickGiftCardByBusinessName(Constants.giftCardItem);
        AssertionHelper.verifyTrue(giftCardPage.getGiftCardHeader().contains(Constants.giftCardItem));
        whoToSendPage= giftCardPage.insertAmount(Constants.amount);
        AssertionHelper.verifyTrue(whoToSendPage.isResultPageHeaderDisplay());
        howToSendPage= whoToSendPage.sendAll(Constants.FriendName,Constants.Bless,Constants.OwnBless, ResourceHelper.getResourcePath("src/main/resources/photos/flower.jpg"));
        AssertionHelper.verifyTrue(howToSendPage.getPageHeader().contains(Constants.howToSendPageHeader));
        loginPage= howToSendPage.sendAll("יולי","21","13:30","s1@walla.com","evyatar");
        AssertionHelper.verifyText(loginPage.getLoginPageHeader(),Constants.loginHeader);
        paymentPage=loginPage.login(Constants.email,Constants.password);
        AssertionHelper.verifyTrue(paymentPage.getPageTitleText());
    }
}
