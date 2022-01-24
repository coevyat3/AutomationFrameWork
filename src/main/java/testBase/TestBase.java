package testBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import helper.browserConfiguration.BrowserType;
import helper.browserConfiguration.ChromeBrowser;
import helper.browserConfiguration.config.ObjectReader;
import helper.browserConfiguration.config.PropertyReader;


import helper.excel.ExcelHelper;
import helper.logger.LoggerHelper;
import helper.resource.ResourceHelper;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ExtentManager;
import helper.wait.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;


public class TestBase {
    public static ExtentReports extent;
    public static ExtentTest test;
    protected static WebDriver driver;
    private Logger log= LoggerHelper.getLogger(testBase.TestBase.class);
    private static File reportDirect;

    public WebDriver getDriver(){
        return this.driver;
    }




    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        extent = ExtentManager.getInstance();

    }

    @Parameters({"browser"})
    public void loadConfig(String browser) throws Exception {
        ObjectReader.reader = new PropertyReader();
        reportDirect = new File(ResourceHelper.getResourcePath("src/main/resources/screenShots"));
        setUpDriver(BrowserType.valueOf(browser));
        test = extent.createTest(getClass().getSimpleName());
        getDriver().get(ObjectReader.reader.getUrl());

    }




    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result, Method method) throws IOException{

        if(result.getStatus() == ITestResult.FAILURE){
            test.log(Status.FAIL, result.getThrowable());
            String imagePath = captureScreen(result.getName(),getDriver());
            test.addScreenCaptureFromPath(imagePath);


        }
        else if(result.getStatus() == ITestResult.SUCCESS){
            test.log(Status.PASS, result.getName()+" is pass");

        }
        else if(result.getStatus() == ITestResult.SKIP){
            test.log(Status.SKIP, result.getThrowable());

        }
        log.info("**************"+result.getName()+"Finished***************");
        extent.flush();
        if(driver!=null){
            getDriver().quit();
        }
    }

    public WebDriver getBrowserObject(BrowserType type) throws Exception{
        try{
            switch (type) {
                case CHROME:
                    ChromeBrowser chrome = new ChromeBrowser().getClass().getDeclaredConstructor().newInstance();
                    ChromeOptions options = chrome.getChromeOptions();
                    return chrome.getChromeDriver(options);


                default : throw new IllegalStateException("driver Not Found"+type.name());
            }
        }catch (Exception e){
            log.info(e.getMessage());
            e.printStackTrace();
            throw e;

        }

    }
    public void setUpDriver(BrowserType type) throws Exception {
        driver=getBrowserObject(type);
        log.info("Internalize Web driver "+driver.hashCode());
        new WaitHelper(getDriver()).setImplicitWait(ObjectReader.reader.getImplicitWait());
        new WaitHelper(getDriver()).getPageLoadTime(ObjectReader.reader.getPageLoadTime());
        getDriver().manage().window().maximize();
    }

    public String captureScreen(String fileName, WebDriver driver)  {
        if(driver == null){
            log.info("driver is null..");
            return null;
        }
        if(fileName==""){
            fileName = "blank";
        }
        Reporter.log("captureScreen method called");
        File destFile = null;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        File screFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            destFile = new File(reportDirect+"/"+fileName+"_"+formater.format(calendar.getTime())+".png");
            Files.copy(screFile.toPath(), destFile.toPath());
            Reporter.log("<a href='"+destFile.getAbsolutePath()+"'><img src='"+destFile.getAbsolutePath()+"'height='100' width='100'/></a>");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return destFile.toString();
    }


    public void getNavigationScreen(WebDriver driver)  {
        log.info("capturing ui navigation screen...");
        String screen = captureScreen("", driver);
        try {
            test.addScreenCaptureFromPath(screen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void logExtentReport(String s){
        test.log(Status.INFO,s);
    }


}
