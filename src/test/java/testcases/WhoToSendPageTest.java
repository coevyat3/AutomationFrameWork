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
        loadConfig(browser);
        navBarPage= new NavBarPage(driver);
        searchResultPage= new SearchResultPage(driver);
        giftCardPage= new GiftCardPage(driver);
        whoToSendPage= new WhoToSendPage(driver);
        howToSendPage= new HowToSendPage(driver);
        searchResultPage= navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
        giftCardPage=searchResultPage.pickGiftCardByBusinessName(Constants.giftCardItem);
        whoToSendPage= giftCardPage.insertAmount(Constants.amount);


    }
    @Test(priority = 0)
    public void verifyWhoToSendPage(){
        AssertionHelper.verifyTrue(whoToSendPage.isResultPageHeaderDisplay());
        AssertionHelper.verifyText(whoToSendPage.getResultPageHeaderText(),Constants.whoToSendPageHeader);
    }
    @Test(priority = 1)
    public void VerifySomeoneElseCheckBox()  {
    AssertionHelper.verifyTrue(whoToSendPage.someoneElse());
    }
    @Test(priority = 2)
    public void verifyForMySelfCheckBoxImgResponse(){
       AssertionHelper.verifyTrue(whoToSendPage.clickOnMySelfCheckBox());
    }
    @Test(priority = 3)
    public void verifyGiftPriceChange(){
     AssertionHelper.verifyText(whoToSendPage.changeGiftPrice("501"),"501" );
    }
    @Test(priority = 4)
    public void setWhoToSendPage(){
        AssertionHelper.verifyTrue(whoToSendPage.someoneElse());
        whoToSendPage.setFriendName(Constants.FriendName);
        whoToSendPage.clickOnBlessDropdown();
        whoToSendPage.pickBless(Constants.Bless);
        whoToSendPage.sendBlessText(Constants.OwnBless);
        whoToSendPage.sendPhoto(ResourceHelper.getResourcePath("src/main/resources/photos/flower.jpg"));

    }

}
