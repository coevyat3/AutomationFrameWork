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
    HomePage homePage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, String browser) throws Exception {
        loadConfig(browser);
        homePage= new HomePage();
         navBarPage= new NavBarPage();
         searchResultPage= new SearchResultPage();
         giftCardPage= new GiftCardPage();
         whoToSendPage= new WhoToSendPage();

    }

    @Test(priority = 1)
    public void verifyGiftCardHeaderTest(){
        searchResultPage=navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
        giftCardPage=searchResultPage.pickGiftCardByBusinessName(Constants.giftCardItem);
        AssertionHelper.verifyTrue(giftCardPage.getGiftCardHeader().contains(Constants.giftCardItem));
        giftCardPage.getReadMore();
    }

    @Test(priority = 2)
    public void VerifyMoneyBox(){
        searchResultPage=navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
        giftCardPage=searchResultPage.pickGiftCardByBusinessName(Constants.giftCardItem);
        giftCardPage.enterAmount(Constants.amount);

    }
    @Test(priority = 3)
    public void catchingJSErrorOnBlankMoneyBox(){;
        searchResultPage=navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
        giftCardPage=searchResultPage.pickGiftCardByBusinessName(Constants.giftCardItem);
        AssertionHelper.verifyTrue(giftCardPage.emptyMoneyInputCatchingJSError());
    }
    @Test(priority = 4)
    public void clickOnBuyMeMulti(){
        homePage.clickOnBuyMeMulti();
        AssertionHelper.verifyTrue(giftCardPage.getGiftCardHeader().contains("BUYME MULTI"));
    }

}
