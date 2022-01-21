package testcases;

import com.aventstack.extentreports.Status;
import helper.assertion.AssertionHelper;
import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import testBase.TestBase;
import org.testng.annotations.Test;
import buymeObjects.Constants;
import buymeObjects.HomePage;

import java.lang.reflect.Method;

public class HomePageTest extends TestBase {
    HomePage homePage;
    Logger log= LoggerHelper.getLogger(HomePageTest.class);

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method,String browser) throws Exception {
        Loader(browser);
        homePage= new HomePage(driver);
        test.log(Status.INFO, method.getName()+"**************test started***************");
        log.info("**************"+method.getName()+"Started***************");
        }

    @Test(priority = 3)
    public void verifyLogoPresent(){
        AssertionHelper.verifyTrue(homePage.isLogoDisplay());
    }

    @Test(priority = 1)
    public void verifySiteURL(){
      AssertionHelper.verifyTrue(homePage.getSiteUrl().equals(Constants.URL));

    }
    @Test(priority = 2)
    public void getHomePageTitle(){
        AssertionHelper.verifyTrue(homePage.getHomePageTitle().contains(Constants.HomePageTitle));
    }



}
