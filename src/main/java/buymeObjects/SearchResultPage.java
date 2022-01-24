package buymeObjects;

import com.aventstack.extentreports.Status;
import helper.browserConfiguration.config.ObjectReader;
import helper.logger.LoggerHelper;
import helper.wait.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.TestBase;

import java.util.List;

import static testBase.TestBase.test;


public class SearchResultPage extends TestBase {

    private Logger log= LoggerHelper.getLogger(SearchResultPage.class);

    public SearchResultPage(){
        PageFactory.initElements(driver,this);

    }
    @FindBy(css=".title-xxl")
    private WebElement searchResultHeader;

    @FindBy(css = "ul[class*='grid'] span")
    private List<WebElement> giftCard;

    public boolean getSearchResultHeader(){
        new WaitHelper(driver).waitForElement(searchResultHeader,ObjectReader.reader.getExplicitWait());
        log.info("Verifying display Search Result Page title: "+searchResultHeader.isDisplayed());
        test.log(Status.INFO,"Verifying display Search Result Page title: "+searchResultHeader.isDisplayed());
        return searchResultHeader.isDisplayed();
    }
    public String getSearchResultTextHeader(){
        new WaitHelper(driver).waitForElement(searchResultHeader,ObjectReader.reader.getExplicitWait());
        log.info("Verifying Search Result Page Header text: "+searchResultHeader.getText());
        test.log(Status.INFO,"Verifying Search Result Page Header text: "+searchResultHeader.getText());
        return searchResultHeader.getText();
    }
    public GiftCardPage pickGiftCardByBusinessName(String txt){
        log.info("Picking Gift Card  From search Result Page "+txt);
        test.log(Status.INFO,"Picking gift card from search Result page "+txt);
     for(WebElement element:giftCard){
         if(element.getText().contains(txt)){
             element.click();
             log.info("Found gift card: "+txt + "from the search result page");
             test.log(Status.INFO,"Found gift card: "+txt + "from the search result page");
             break;
         }
     }return new GiftCardPage();
    }


}
