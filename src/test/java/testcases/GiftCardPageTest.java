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
        loadConfig(browser);
         navBarPage= new NavBarPage();
         searchResultPage= new SearchResultPage();
         giftCardPage= new GiftCardPage();
         whoToSendPage= new WhoToSendPage();
         searchResultPage=navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
         giftCardPage=searchResultPage.pickGiftCardByBusinessName(Constants.giftCardItem);
    }

    @Test(priority = 1,enabled = false)
    public void verifyGiftCardHeaderTest(){
      AssertionHelper.verifyTrue(giftCardPage.getGiftCardHeader().contains(Constants.giftCardItem));
    }
    @Test(priority = 2,enabled = false)
    public void readMoreAboutProduct() throws InterruptedException {
      giftCardPage.setReadMore();
    }
    @Test(priority = 3,enabled = false)
    public void VerifyMoneyBox() throws InterruptedException {
        giftCardPage.enterAmount(Constants.amount);

    }
    @Test(priority = 4)
    public void catchingJSErrorOnBlankMoneyBox(){;
        AssertionHelper.verifyTrue(giftCardPage.verifyJSError());
    }

}
