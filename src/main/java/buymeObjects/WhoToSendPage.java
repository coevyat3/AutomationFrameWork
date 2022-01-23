package buymeObjects;

import com.aventstack.extentreports.Status;
import helper.browserConfiguration.config.ObjectReader;
import helper.logger.LoggerHelper;
import helper.verification.VerificationHelper;
import helper.wait.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static testBase.TestBase.test;

public class WhoToSendPage {
    WebDriver driver;
    Logger log= LoggerHelper.getLogger(WhoToSendPage.class);

    public WhoToSendPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "label#friendName>input")
    private WebElement friendName;

    @FindBy(css = ".selected-name span")
    private WebElement selectedBlessClick;

    @FindBy(css = ".selected-name+.dropdown span")
    private List<WebElement> blessPool;

    @FindBy(css="span.label+textarea")
    private WebElement textarea;

    @FindBy(css = "input[type='file']")
    private WebElement photo;

    @FindBy(css = "button[type='submit']")
    private WebElement btn;


    @FindBy(css="div[class*=forSomeone]")
    private WebElement someoneElse;

    @FindBy(css="div[class*=forMyself]")
    private WebElement forMySelf;

    @FindBy(css="img[class*=gift-myself]")
    private WebElement imgForMySelf;

    @FindBy(css="div[class*=price-label]")
    private WebElement price;

    @FindBy(css="input[type='tel']")
    private WebElement changePrice;

    @FindBy(css = "div[class*='mx-6']>button")
    private WebElement confirmPriceChange;

    @FindBy(css="div.step.active>div.label")
    private WebElement header;

    public HowToSendPage sendAll(String t,String a,String b,String photo){
        new WaitHelper(driver).waitForElement(friendName, ObjectReader.reader.getExplicitWait());
        setFriendName(t);
        selectedBlessClick.click();
        pickBless(a);
        sendBlessText(b);
        sendPhoto(photo);
        clickBtn();
        return new HowToSendPage(driver);
    }
    public void pickBless(String txt){
        for(WebElement element:blessPool){
            if(element.getText().contains(txt)){
                element.click();
                log.info("Picking a blessing from dropdown menu "+txt);
                test.log(Status.INFO,"Picking a  blessing from dropdown menu"+txt);
                break;
            }
        }
    }
    public void clickOnBlessDropdown(){
        selectedBlessClick.click();
    }
    public void setFriendName(String txt){
        log.info("Send friend name into textBox: "+txt);
        test.log(Status.INFO,"Send friend name into textBox: "+txt);
        friendName.sendKeys(txt);
    }
    public void sendBlessText(String txt){
        log.info("Clearing suggest text blessing& adding new Bless into textBox: "+txt);
        test.log(Status.INFO,"Clearing suggest text blessing& adding new Bless into textBox: "+txt);
        textarea.clear();
        textarea.sendKeys(txt);
    }
    public void sendPhoto(String photo){
        log.info("Upload a photo ..");
        test.log(Status.INFO,"upload a photo..");
        this.photo.sendKeys(photo);
    }
    public void clickBtn(){
        log.info("Clicking Button "+btn.getText());
        test.log(Status.INFO,"Clicking Button "+btn.getText());
        btn.click();
    }
    public boolean isResultPageHeaderDisplay(){
        log.info("Verify  Who to send Page header status "+ header.isDisplayed());
        test.log(Status.INFO,"Verify  Who to send Page header status "+ header.isDisplayed());
        return new VerificationHelper(driver).isDisplayed(header);

    }
    public String getResultPageHeaderText(){
        log.info("Verify  Who to send Page header text "+ header.getText());
        test.log(Status.INFO,"Verify  Who to send Page header text "+ header.getText());
        return new VerificationHelper(driver).getText(header);
    }
    public boolean someoneElse(){
        return new VerificationHelper(driver).isSelected(someoneElse);
    }
    public boolean clickOnMySelfCheckBox(){
        forMySelf.click();
       return new VerificationHelper(driver).isDisplayed(imgForMySelf);
    }
    public String changeGiftPrice(String txt){
        setPrice();
        setChangePrice(txt);
        setConfirmPriceChange();
        return price.getText().substring(1);

    }
    public void setPrice(){
        price.click();
    }
    public void setChangePrice(String txt){
        changePrice.clear();
        changePrice.sendKeys(txt);
    }
    public void setConfirmPriceChange(){
        confirmPriceChange.click();
    }



}
