package helper.browserConfiguration.config;

import helper.browserConfiguration.BrowserType;

public interface ConfigReader {
    public int getImplicitWait();
    public int getExplicitWait();
    public int getPageLoadTime();
    public String getUrl();
}
