package testcases;

import helper.assertion.AssertionHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import testBase.TestBase;
import org.testng.annotations.Test;
import buymeObjects.*;

import java.lang.reflect.Method;

public class SearchResultTest extends TestBase {
    NavBarPage navBarPage;
    SearchResultPage searchResultPage;
    GiftCardPage giftCardPage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, String browser) throws Exception {
        Loader(browser);
        navBarPage= new NavBarPage(driver);
        searchResultPage= new SearchResultPage(driver);
        searchResultPage= navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
        giftCardPage= new GiftCardPage(driver);
    }

    @Test(priority = 1)
    public void verifySearchResultHeaderPage() {
        AssertionHelper.verifyTrue(searchResultPage.getSearchResultTextHeader().contains(Constants.amount));
        AssertionHelper.verifyTrue(searchResultPage.getSearchResultTextHeader().contains(Constants.category));
        AssertionHelper.verifyTrue(searchResultPage.getSearchResultTextHeader().contains(Constants.area));

    }
    @Test(priority = 2)
    public void pickGiftCardFromSearchResultPage()  {
        giftCardPage=searchResultPage.pickGiftCard(Constants.giftCardItem);
        AssertionHelper.verifyTrue(giftCardPage.getGiftCardHeader().contains(Constants.giftCardItem));

    }

}
