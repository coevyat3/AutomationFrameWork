package testcases;

import helper.assertion.AssertionHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import testBase.TestBase;
import org.testng.annotations.Test;
import buymeObjects.*;

import java.lang.reflect.Method;

public class GiftCardPageTest extends TestBase {
    NavBarPage navBarPage;
    SearchResultPage searchResultPage;
    GiftCardPage giftCardPage;
    WhoToSendPage whoToSendPage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, String browser) throws Exception {
        Loader(browser);
         navBarPage= new NavBarPage(driver);
         searchResultPage= new SearchResultPage(driver);
         giftCardPage= new GiftCardPage(driver);
         whoToSendPage= new WhoToSendPage(driver);
         searchResultPage=navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
         giftCardPage=searchResultPage.pickGiftCard(Constants.giftCardItem);
    }
    @Test(priority = 1)
    public void insertAmount(){
        whoToSendPage=giftCardPage.insertAmount(Constants.amount);
        AssertionHelper.verifyTrue(whoToSendPage.isResultPageHeaderDisplay());
    }
}
