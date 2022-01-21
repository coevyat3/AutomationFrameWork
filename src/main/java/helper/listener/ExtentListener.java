package helper.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import helper.logger.LoggerHelper;
import utils.ExtentManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ExtentListener implements ITestListener {
    public static ExtentReports extent;
    public static ExtentTest test;
    private Logger log= LoggerHelper.getLogger(ExtentListener.class);

    @Override
    public void onStart(ITestContext context) {
        extent = ExtentManager.getInstance();
        test = extent.createTest(context.getName());
        Reporter.log(context.getCurrentXmlTest().getName()+"Class Started...");


    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        log.info(context.getName()+" Test Finished");

    }

    @Override
    public void onTestStart(ITestResult result) {
        test.log(Status.INFO, result.getName() + " started..");
        log.info("Test started");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.INFO, result.getName() + " Passed..");
        Reporter.log(result.getMethod().getMethodName()+" Test Success");
        log.info(result.getMethod().getMethodName()+" Test Success ");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.INFO, result.getName() + " Skipped.." + result.getThrowable());
        Reporter.log(result.getMethod().getMethodName()+" Test Skipped");
        log.warn(result.getMethod().getMethodName()+" Test Skipped "+result.getThrowable());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.INFO, result.getName() + "Failed.." + result.getThrowable());
        Reporter.log(result.getMethod().getMethodName()+" Test skipped");
        log.error(result.getMethod().getMethodName()+" Test Failed "+result.getThrowable());
    }
}

