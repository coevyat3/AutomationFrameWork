package helper.frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameHelper {
    private WebDriver driver;
    private Logger log;
    public FrameHelper(WebDriver driver){
        this.driver=driver;
    }

    /**
     * Switched to frame based of frame index
     * @param index
     */

    public void switchToFrame(int index){
        driver.switchTo().frame(index);
        log.info("switched to :"+index+" frame");
    }

    /**
     * Switched to frame based on his name
     * @param name
     */
    public void switchToFrame(String name){
        driver.switchTo().frame(name);
        log.info("switched to :"+name+" frame");
    }

    /**
     * Switch to frame based on his WebElement
     * @param element
     */
    public void switchToFrame(WebElement element){
        driver.switchTo().frame(element);
        log.info("switched to frame:"+element.toString());
    }





}
