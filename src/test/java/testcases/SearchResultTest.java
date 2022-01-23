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
    HomePage homePage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, String browser) throws Exception {
        loadConfig(browser);
        homePage= new HomePage(driver);
        navBarPage= new NavBarPage(driver);
        searchResultPage= new SearchResultPage(driver);

    }

    @Test(priority = 1,description = "Pick A gift Card from NavBar")
    public void verifySearchResultHeaderPage() {
        searchResultPage= navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
        AssertionHelper.verifyTrue(searchResultPage.getSearchResultTextHeader().contains(Constants.amount));
        AssertionHelper.verifyTrue(searchResultPage.getSearchResultTextHeader().contains(Constants.category));
        AssertionHelper.verifyTrue(searchResultPage.getSearchResultTextHeader().contains(Constants.area));


    }
    @Test(priority = 2,description = "Pick Gift Card from Top categories")
    public void pickGiftCardFromTopCategories()  {
        homePage.pickGiftFromTopCategories(Constants.birthDayCategory);
        AssertionHelper.verifyTrue(searchResultPage.getSearchResultTextHeader().contains(Constants.birthDayCategory));
    }
    @Test(priority = 3,description = "Pick gift Card from main Categories")
    public void pickGiftFromMainCategories(){
        homePage.pickGiftFromMainCategory(Constants.category);
        AssertionHelper.verifyTrue(searchResultPage.getSearchResultTextHeader().contains(Constants.category));
    }




}
