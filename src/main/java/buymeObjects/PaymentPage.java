package buymeObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {
    WebDriver driver;
    public PaymentPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
