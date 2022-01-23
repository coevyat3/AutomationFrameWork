package buymeObjects;

import helper.browserConfiguration.config.ObjectReader;
import helper.verification.VerificationHelper;
import helper.wait.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {
    WebDriver driver;
    public PaymentPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="div.total-price")
    private WebElement pageHeader;


    public boolean getPageTitleText(){
        new WaitHelper(driver).waitForElement(pageHeader, ObjectReader.reader.getExplicitWait());
    return new VerificationHelper(driver).isDisplayed(pageHeader);
    }

}
