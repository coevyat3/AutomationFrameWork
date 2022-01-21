package testcases;

import helper.assertion.AssertionHelper;
import helper.resource.ResourceHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import testBase.TestBase;
import org.testng.annotations.Test;
import buymeObjects.*;

import java.lang.reflect.Method;

public class WhoToSendPageTest extends TestBase {
    NavBarPage navBarPage;
    SearchResultPage searchResultPage;
    GiftCardPage giftCardPage;
    WhoToSendPage whoToSendPage;
    HowToSendPage howToSendPage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, String browser) throws Exception {
        Loader(browser);
        navBarPage= new NavBarPage(driver);
        searchResultPage= new SearchResultPage(driver);
        giftCardPage= new GiftCardPage(driver);
        whoToSendPage= new WhoToSendPage(driver);
        howToSendPage= new HowToSendPage(driver);
        searchResultPage= navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
        giftCardPage=searchResultPage.pickGiftCard(Constants.giftCardItem);
        whoToSendPage= giftCardPage.insertAmount(Constants.amount);



    }
    @Test(priority = 3)
    public void setWhoToSendPage()  {
        whoToSendPage.sendAll(Constants.FriendName,Constants.Bless,Constants.OwnBless,ResourceHelper.getResourcePath("src/main/resources/photos/flower.jpg"));
        AssertionHelper.verifyTrue(howToSendPage.getWhoToSendPageHeader());

    }
}
