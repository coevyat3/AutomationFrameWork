package helper.browserConfiguration.config;

import helper.browserConfiguration.BrowserType;
import helper.resource.ResourceHelper;

import java.io.*;
import java.util.Properties;

public class PropertyReader implements ConfigReader {
    public static Properties pro;
    public static FileInputStream file;
  public PropertyReader(){
        try {
            file = new FileInputStream(ResourceHelper.getResourcePath("src/main/resources/configFiles/config.properties"));
            pro=new Properties();
            pro.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        @Override
    public int getImplicitWait() {
        return Integer.parseInt(pro.getProperty("implicitWait"));
    }

    @Override
    public int getExplicitWait() {
        return  Integer.parseInt(pro.getProperty("explicitWait"));
    }

    @Override
    public int getPageLoadTime() {
        return Integer.parseInt(pro.getProperty("pageLoadTime"));
    }



    @Override
    public String getUrl() {
        return pro.getProperty("url");
    }
}
