package helper.browserConfiguration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeBrowser {

    public ChromeOptions getChromeOptions(){
        ChromeOptions option= new ChromeOptions();
        option.addArguments("--test-type");
        option.addArguments("--disable-popup-blocking");
        DesiredCapabilities chrome= new DesiredCapabilities();
        chrome.setJavascriptEnabled(true);
        option.setCapability(ChromeOptions.CAPABILITY,option);
        return  option;
    }
    public WebDriver getChromeDriver(ChromeOptions cap){
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver(cap);
        return driver;
    }


}
