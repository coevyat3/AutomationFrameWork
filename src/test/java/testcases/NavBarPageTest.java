package testcases;

import helper.assertion.AssertionHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import testBase.TestBase;
import org.testng.annotations.Test;
import buymeObjects.*;

import java.lang.reflect.Method;


public class NavBarPageTest extends TestBase {
    NavBarPage navBarPage;
    SearchResultPage searchResultPage;


    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method, String browser) throws Exception {
        Loader(browser);
            navBarPage= new NavBarPage(driver);
            searchResultPage= new SearchResultPage(driver);
        }
        @Test(priority = 1)
        public void pickGiftFromNavBar(){
            navBarPage.pickItem(Constants.amount,Constants.area,Constants.category);
            AssertionHelper.verifyTrue(searchResultPage.getSearchResultHeader());
        }
        }