package testcases;

import helper.assertion.AssertionHelper;
import helper.verification.VerificationHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import testBase.TestBase;
import org.testng.annotations.Test;
import buymeObjects.*;
import utils.ReadingSheets;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;


public class NavBarPageTest extends TestBase {
    NavBarPage navBarPage;
    SearchResultPage searchResultPage;


    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, String browser) throws Exception {
        loadConfig(browser);
            navBarPage= new NavBarPage(driver);
            searchResultPage= new SearchResultPage(driver);
        }
        @Test(priority = 1,description = "pick a gift card with Valid values ")
        public void pickGiftFromNavBar(){
            navBarPage.clickAmount();
            navBarPage.clickActiveList(Constants.amount);
            navBarPage.clickArea();
            navBarPage.clickActiveList(Constants.area);
            navBarPage.clickCategory();
            navBarPage.clickActiveList(Constants.category);
        }

        @Test(priority = 2,groups = {"dataDrivenTest"} ,dataProvider = "searchBoxGift")
        public void pickGiftFromInputBox(String txt){
        navBarPage.searchFromInputBox(txt);
        AssertionHelper.verifyTrue(searchResultPage.getSearchResultHeader());

    }
     @Test(priority = 2,description = "Test navbar inputBox with autoComplete suggest Pick")
     public void searchGiftFromInputBoxSuggest(){
       navBarPage.searchFromInputBoxAutoComplete(Constants.autoComplete,Constants.autoSuggestPick);
     }


    @DataProvider(name="searchBoxGift")
    public Iterator<Object[]> sendLoginData(){
        ArrayList<Object[]> data = ReadingSheets.getCartData();
        return data.iterator();
    }
}