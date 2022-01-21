package helper.window;


import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowHelper {
    private WebDriver driver;
    private Logger log = LoggerHelper.getLogger(WindowHelper.class);

    public WindowHelper(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Switching to parent window
     */
    public void switchToParentWindow() {
        log.info("Switching to parent window");
        driver.switchTo().defaultContent();
    }

    /**
     * Switch to window according to his index
     *
     * @param index
     */
    public void switchToWindow(int index) {
        log.info("Switching to parent window");
        Set<String> windows = driver.getWindowHandles();
        int i = 1;
        for (String window : windows) {
            if (i == index) {
                driver.switchTo().window(window);
            } else {
                i++;
            }
        }

    }

    /**
     * Close all the table windows
     * and switch to main window
     */

    public void closeAllTabsAndSwitchToMainWindow() {
        log.info("close All Tabs And Switch To Main Window");
        Set<String> windows = driver.getWindowHandles();
        String mainWindow = driver.getWindowHandle();
        int i = 1;
        for (String window : windows) {
            if (!window.equalsIgnoreCase(mainWindow)) {
                driver.close();
            } else {
                i++;
            }
        }driver.switchTo().window(mainWindow);
    }
    public void navigateBack(){
        log.info("navigateBack");
        driver.navigate().back();
    }
}