package buymeObjects;

import com.aventstack.extentreports.Status;
import helper.logger.LoggerHelper;
import helper.verification.VerificationHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.TestBase;


import java.util.List;
import java.util.NoSuchElementException;



public class HomePage extends TestBase {
    private Logger log= LoggerHelper.getLogger(HomePage.class);

    public HomePage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css ="li[class='notSigned'] a" )
    private WebElement loginBtn;

    @FindBy(css = "div[class*='nav'] img")
    private WebElement logoImg;

    @FindBy(css="div[class='top-category-tabs'] >a")
    private List<WebElement> topCategoriesList;

    @FindBy(css="div[class*='home-wrapper'] a")
    private List<WebElement> categoriesList;

    @FindBy(css="li[gtm] img")
    private WebElement multi;


    public String getHomePageTitle(){
        log.info("Checking HomePage title");
        test.log(Status.INFO,"Checking HomePage title");
       return  new VerificationHelper(driver).getPageTitle();

    }
    public boolean isLogoDisplay(){
        log.info("Checking logoImg Display "+logoImg);
        test.log(Status.INFO,"Checking logo img display ");
        return new VerificationHelper(driver).isDisplayed(logoImg);
    }
    public String getSiteUrl(){
        log.info("Return homePage URL");
        test.log(Status.INFO,"Return HomePage URL");
        return driver.getCurrentUrl();
    }

    public SearchResultPage pickGiftFromTopCategories(String txt){
           for(WebElement element: topCategoriesList){
               if(element.getAttribute("title").contains(txt)){
                   element.click();
                   log.info("Picking gift from HomePage Main category: "+element.getAttribute("title"));
                   test.log(Status.INFO,"Picking gift from HomePage Main category "+element.getAttribute("title"));
                   break;
               }
           }
           return new SearchResultPage();
    }
    public SearchResultPage pickGiftFromMainCategory(String title){
        for(WebElement element:categoriesList){
            if(element.getAttribute("title").contains(title)){
                Actions actions= new Actions(driver);
                actions.moveToElement(element).perform();
                element.click();
                break;
            }
        }return new SearchResultPage();
    }
    public GiftCardPage clickOnBuyMeMulti(){
        boolean flag=new VerificationHelper(driver).isDisplayed(multi);
        if(flag){
           Actions actions= new Actions(driver);
           actions.moveToElement(multi).perform();
           multi.click();
           return new GiftCardPage();
       }
       else throw new NoSuchElementException();
    }



    public LoginPage gotoLogin(){
        loginBtn.click();
        return new LoginPage();
    }




}
