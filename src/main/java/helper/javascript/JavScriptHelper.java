package helper.javascript;


import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavScriptHelper {
    private WebDriver driver;
    private  Logger log= LoggerHelper.getLogger(JavScriptHelper.class);

    public JavScriptHelper(WebDriver driver){
        this.driver=driver;
    }
    public Object executeScript(String script){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return jse.executeScript(script);
    }
    public Object executeScript(String script, Object... args){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return jse.executeScript(script,args);
    }
    public void scrollToElement(WebElement element){
        log.info("Scrolling to WebElement..");
        executeScript("window.scrollTo(arguments[0],argument][1])",element.getLocation().x,element.getLocation().y);
    }
    public void scrollToElementAndClick(WebElement element){
        scrollToElement(element);
        element.click();
        log.info("element is clicked");
    }
    public void scrollInToView(WebElement element){
        executeScript("arguments[0].scrollIntoView()",element);
    }
    public void scrollIntoViewAndClick(WebElement element){
        scrollInToView(element);
        log.info("element is clicked");
    }
    public void scrollDownVertically(){
        log.info("scrolling down vertically");
        executeScript("window.scrollTo(0,document.body.scrollHeight)");

    }
    public void scrollUpVertically(){
        log.info("scrolling dup vertically");
        executeScript("window.scrollTo(0,-document.body.scrollHeight)");

    }
    public void scrollDownByPixel(int pixel){
        log.info("ScrollDown by pixel"+pixel);
        executeScript("window.scrollBy(0)",+pixel+")");
    }
    public void scrollUpByPixel(int pixel){
        log.info("ScrollUP by pixel"+pixel);
        executeScript("window.scrollBY(0,-"+pixel+")");
    }







}
