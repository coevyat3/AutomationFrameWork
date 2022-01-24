package helper.browserConfiguration.config;



public interface ConfigReader {
    public int getImplicitWait();
    public int getExplicitWait();
    public int getPageLoadTime();
    public String getUrl();
}
