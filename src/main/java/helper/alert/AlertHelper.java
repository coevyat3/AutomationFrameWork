package helper.alert;

import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;



public class AlertHelper {
    private WebDriver driver;
   private  Logger log= LoggerHelper.getLogger(AlertHelper.class);

    public AlertHelper(WebDriver driver) {
        this.driver = driver;

    }
    public Alert getAlert() {
        log.info("Switch to alert"+driver.switchTo().alert().getText());
        return driver.switchTo().alert();
    }
    public void acceptAlert(){
        log.info("Accept Alert");
        getAlert().accept();
    }
    public void dismissAlert(){
        log.info("Dismiss Alert");
        getAlert().dismiss();
    }
    public String getAlertText(){
        String msg=getAlert().getText();
        log.info("Get alert Text");
        return msg;
    }
    public boolean isAlertPresent(){
        try{
            driver.switchTo().alert();
            log.info("alert is present");
            return true;
        }catch (NoAlertPresentException e){
            e.printStackTrace();
            log.info("No Alert Present "+e.getCause());
            return  false;
        }
    }
    public void acceptAlertIfPresent(){
        if(isAlertPresent()){
            acceptAlert();
        }
        else{
            log.info("Alert is not present");
    }
    }
    public void acceptPrompt(String msg){
        if(isAlertPresent()){
            Alert alert=getAlert();
            alert.sendKeys(msg);
            alert.accept();
            log.info("Alert Present ,send text to alert: "+msg);
        }

    }



}
